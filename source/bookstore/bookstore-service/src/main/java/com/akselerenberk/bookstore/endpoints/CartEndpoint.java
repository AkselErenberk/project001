package com.akselerenberk.bookstore.endpoints;

import com.akselerenberk.bookstore.api.CartApi;
import com.akselerenberk.bookstore.core.ports.CartPort;
import com.akselerenberk.bookstore.dto.AddToCartRequestDTO;
import com.akselerenberk.bookstore.dto.CartDTO;
import com.akselerenberk.bookstore.dto.UpdateQuantityRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CartEndpoint implements CartApi {

    private final CartPort cartPort;

    @Override
    public ResponseEntity<Void> addBookQuantity(AddToCartRequestDTO addToCartRequestDTO) {
        cartPort.addCartItem(addToCartRequestDTO.getBookId(), addToCartRequestDTO.getQuantity());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> removeBook(String bookId) {
        cartPort.deleteCartItem(bookId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CartDTO> retrieveCart() {
        val cartDTO = cartPort.retrieveCart();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateBookQuantity(String bookId, UpdateQuantityRequestDTO updateQuantityRequestDTO) {
        return ResponseEntity.ok().build();
    }
}
