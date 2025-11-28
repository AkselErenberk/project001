package com.akselerenberk.bookstore.core.models;

import lombok.Builder;

@Builder
public record Credentials(
        String username,
        String password) {
}