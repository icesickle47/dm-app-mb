package com.mak.dm_api.api;

import java.time.Instant;
import java.util.UUID;

public record SendMessageResponse(
        UUID id,
        UUID conversationId,
        String senderId,
        String recipientId,
        String clientMessageId,
        String text,
        Instant createdAt
) {}
