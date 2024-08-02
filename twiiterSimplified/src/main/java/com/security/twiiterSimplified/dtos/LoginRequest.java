package com.security.twiiterSimplified.dtos;

public record LoginRequest(
        String username,
        String password
) {
}
