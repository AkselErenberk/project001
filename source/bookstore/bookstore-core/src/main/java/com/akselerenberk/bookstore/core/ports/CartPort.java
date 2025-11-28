package com.akselerenberk.bookstore.core.ports;

import com.akselerenberk.bookstore.core.models.Cart;
import com.akselerenberk.bookstore.core.models.CartItem;
import org.springframework.stereotype.Service;

@Service
public interface CartPort {

    Cart retrieveCart();

    void addCartItem(String bookId, Integer quantity);

    void updateCartItem(String bookId, Integer quantity);

    void deleteCartItem(String bookId);
}