package com.akselerenberk.bookstore.core.models;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder(toBuilder = true)
public record Cart(
        List<CartItem> cartItems,
        BigDecimal totalPrice
) {
}