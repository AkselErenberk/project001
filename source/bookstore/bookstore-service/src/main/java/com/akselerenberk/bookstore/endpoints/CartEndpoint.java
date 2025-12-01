package com.akselerenberk.bookstore.endpoints;

import com.akselerenberk.bookstore.api.CartApi;
import com.akselerenberk.bookstore.core.ports.CartPort;
import com.akselerenberk.bookstore.dto.CartDTO;
import com.akselerenberk.bookstore.dto.NewCartItemRequestDTO;
import com.akselerenberk.bookstore.dto.UpdateCartItemRequestDTO;
import com.akselerenberk.bookstore.mappers.CartMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CartEndpoint implements CartApi {

    private final CartPort cartPort;

    @Override
    public ResponseEntity<CartDTO> addCartItem(NewCartItemRequestDTO newCartItemRequestDTO) {
        cartPort.addItem(newCartItemRequestDTO.getBookId(), newCartItemRequestDTO.getQuantity());
        val cart = cartPort.retrieveCart();
        return ResponseEntity.status(HttpStatus.CREATED).body(CartMapper.dto(cart));
    }

    @Override
    public ResponseEntity<Void> removeCartItem(String bookId) {
        cartPort.deleteItem(bookId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CartDTO> retrieveCart() {
        val cart = cartPort.retrieveCart();
        return ResponseEntity.ok().body(CartMapper.dto(cart));
    }

    @Override
    public ResponseEntity<CartDTO> updateCartItem(String bookId, UpdateCartItemRequestDTO updateCartItemRequestDTO) {
        return retrieveCart();
    }
}
