package com.akselerenberk.bookstore.core.models;

import lombok.Builder;

import java.math.BigDecimal;

@Builder(toBuilder = true)
public record Book(
        String id,
        String title,
        String author,
        BigDecimal price
) {
}