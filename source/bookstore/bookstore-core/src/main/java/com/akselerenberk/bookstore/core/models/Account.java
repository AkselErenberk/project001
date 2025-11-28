package com.akselerenberk.bookstore.core.models;

import lombok.Builder;

import java.util.Set;

@Builder(toBuilder = true)
public record Account(
        String username,
        String password,
        VerificationLevel verificationLevel,
        Set<Role> roles
) {
}