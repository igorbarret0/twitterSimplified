package com.security.twiiterSimplified.dtos;

public record FeedItemDto(
        Long tweetId,
        String content,
        String username
) {
}
