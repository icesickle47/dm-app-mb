package com.mak.dm_api.api.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public class MessageResponse {
    private UUID id;
    private Long conversationId;
    private Long senderUserId;
    private String content;
    private OffsetDateTime createdAt;

    public MessageResponse(UUID id, Long conversationId, Long senderUserId, String content, OffsetDateTime createdAt) {
        this.id = id;
        this.conversationId = conversationId;
        this.senderUserId = senderUserId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public Long getConversationId() { return conversationId; }
    public Long getSenderUserId() { return senderUserId; }
    public String getContent() { return content; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
}
