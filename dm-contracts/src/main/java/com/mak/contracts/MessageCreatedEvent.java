package com.mak.contracts;

public record MessageCreatedEvent(
        String messageId,
        String conversationId,
        String senderId,
        String recipientId,
        String text,
        long createdAtEpochMs
) {}
