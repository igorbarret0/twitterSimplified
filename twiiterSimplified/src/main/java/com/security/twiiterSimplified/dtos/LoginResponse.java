package com.security.twiiterSimplified.dtos;

public record LoginResponse(
        String accessToken,
        Long expiresIn
) {
}
