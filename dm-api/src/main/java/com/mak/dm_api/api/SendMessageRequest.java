package com.mak.dm_api.api;

import java.util.UUID;

public record SendMessageRequest(
        UUID conversationId,
        String senderId,
        String recipientId,
        String clientMessageId,
        String text
) {}
