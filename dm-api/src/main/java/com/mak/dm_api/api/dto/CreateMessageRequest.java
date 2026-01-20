package com.mak.dm_api.api.dto;

public class CreateMessageRequest {
    private Long senderUserId;
    private String content;
    private String clientMessageId; // optional but useful later

    public Long getSenderUserId() { return senderUserId; }
    public void setSenderUserId(Long senderUserId) { this.senderUserId = senderUserId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getClientMessageId() { return clientMessageId; }
    public void setClientMessageId(String clientMessageId) { this.clientMessageId = clientMessageId; }
}
