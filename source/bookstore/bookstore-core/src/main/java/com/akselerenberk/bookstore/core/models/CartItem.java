package com.akselerenberk.bookstore.core.models;

import lombok.Builder;

import java.math.BigDecimal;

@Builder(toBuilder = true)
public record CartItem(
        Integer quantity,
        Book book,
        BigDecimal totalPrice
) {
}