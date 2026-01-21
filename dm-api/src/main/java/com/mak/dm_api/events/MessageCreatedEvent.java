package com.mak.dm_api.events;

import java.time.OffsetDateTime;

// this is to be moved to dm-contracts later so that we have a common message creation contract
// both dm-api and dm-deliver will share this file later on
// this is here right now just to make sure we are able to test an end to end endpoint

public class MessageCreatedEvent {
    private Long messageId;
    private Long conversationId;
    private Long senderUserId;
    private String content;
    private OffsetDateTime createdAt;

    public MessageCreatedEvent() {}

    public MessageCreatedEvent(Long messageId, Long conversationId, Long senderUserId, String content, OffsetDateTime createdAt) {
        this.messageId = messageId;
        this.conversationId = conversationId;
        this.senderUserId = senderUserId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getMessageId() { return messageId; }
    public void setMessageId(Long messageId) { this.messageId = messageId; }

    public Long getConversationId() { return conversationId; }
    public void setConversationId(Long conversationId) { this.conversationId = conversationId; }

    public Long getSenderUserId() { return senderUserId; }
    public void setSenderUserId(Long senderUserId) { this.senderUserId = senderUserId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
