package com.mak.dm_api.api;

import java.util.UUID;

public record SendMessageRequest(
        String senderId,
        String recipientId,
        String clientMessageId,
        String text
) {}
