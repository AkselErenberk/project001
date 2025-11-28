package com.akselerenberk.bookstore.core.models;

import lombok.Builder;

@Builder
public record UserPublic(
        String username,
        VerificationLevel verificationLevel
) {
}