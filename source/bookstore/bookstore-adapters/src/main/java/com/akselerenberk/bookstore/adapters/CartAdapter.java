package com.akselerenberk.bookstore.adapters;

import com.akselerenberk.bookstore.adapters.mappers.CartMapper;
import com.akselerenberk.bookstore.adapters.services.AccountService;
import com.akselerenberk.bookstore.common.exception.BadRequestException;
import com.akselerenberk.bookstore.core.models.Cart;
import com.akselerenberk.bookstore.core.ports.CartPort;
import com.akselerenberk.bookstore.database.entities.CartItemEntity;
import com.akselerenberk.bookstore.database.repositories.BookRepository;
import com.akselerenberk.bookstore.database.repositories.CartItemRepository;
import com.akselerenberk.bookstore.database.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartAdapter implements CartPort {

    private final CartRepository cartRepository;
    private final AccountService accountService;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;

    @Override
    public Cart retrieveCart() {
        val currentUser = accountService.retrieveCurrentAccount().orElseThrow();
        return cartRepository.findByAccount(currentUser).map(CartMapper::model)
                .orElse(Cart.builder().items(List.of()).totalPrice(BigDecimal.ZERO).build());
    }

    @Override
    public void addItem(String bookId, Integer quantity) {
        val currentUser = accountService.retrieveCurrentAccount().orElseThrow();
        val cartEntity = cartRepository.findByAccount(currentUser).orElseThrow();
        val bookEntity = bookRepository.findById(parseLongOrThrow(bookId)).orElseThrow();
        val cartItemEntity = CartItemEntity.builder().cart(cartEntity).book(bookEntity).quantity(quantity).build();
        cartItemRepository.save(cartItemEntity);
    }

    @Override
    public void updateItem(String bookId, Integer quantity) {
        val currentUser = accountService.retrieveCurrentAccount().orElseThrow();
        val cartEntity = cartRepository.findByAccount(currentUser).orElseThrow();
        val bookEntity = bookRepository.findById(parseLongOrThrow(bookId)).orElseThrow();
        val cartItemEntity = CartItemEntity.builder().cart(cartEntity).book(bookEntity).quantity(quantity).build();
        cartItemRepository.save(cartItemEntity);
    }

    @Override
    public void deleteItem(String bookId) {
        val currentUser = accountService.retrieveCurrentAccount().orElseThrow();
        val cartEntity = cartRepository.findByAccount(currentUser).orElseThrow();
        val bookEntity = bookRepository.findById(parseLongOrThrow(bookId)).orElseThrow();
        cartItemRepository.findByBookAndCart(bookEntity, cartEntity).ifPresent(cartItemEntity -> {
            cartEntity.removeItem(cartItemEntity);
            cartItemRepository.delete(cartItemEntity);
            cartRepository.save(cartEntity);
        });
    }

    private Long parseLongOrThrow(String bookId) {
        try {
            return Long.valueOf(bookId);
        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid bookId format: " + bookId);
        }
    }
}
