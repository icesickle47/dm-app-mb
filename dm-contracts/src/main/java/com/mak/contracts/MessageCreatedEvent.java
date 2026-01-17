package com.mak.contracts;

public record MessageCreatedEvent(
        String schemaVersion,
        String messageId,
        String clientMessageId,
        String conversationId,
        String senderId,
        String recipientId,
        String text,
        long createdAtEpochMs
) {}

