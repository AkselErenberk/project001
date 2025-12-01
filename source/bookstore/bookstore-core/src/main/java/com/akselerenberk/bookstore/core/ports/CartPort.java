package com.akselerenberk.bookstore.core.ports;

import com.akselerenberk.bookstore.core.models.Cart;
import org.springframework.stereotype.Service;

@Service
public interface CartPort {

    Cart retrieveCart();

    void addItem(String bookId, Integer quantity);

    void updateItem(String bookId, Integer quantity);

    void deleteItem(String bookId);
}