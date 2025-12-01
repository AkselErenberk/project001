package com.akselerenberk.bookstore.core.ports;

import com.akselerenberk.bookstore.core.models.Cart;
import org.springframework.stereotype.Service;

@Service
public interface CartPort {

    Cart retrieveCart();

    Cart addItem(String bookId, Integer quantity);

    Cart updateItem(String bookId, Integer quantity);

    Cart deleteItem(String bookId);
}