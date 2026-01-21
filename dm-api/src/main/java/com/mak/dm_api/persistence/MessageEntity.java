package com.mak.dm_api.persistence;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "messages",
        indexes = {
                @Index(name = "idx_messages_conversation_created", columnList = "conversation_id, created_at")
        },
        uniqueConstraints = {
                // Idempotency: clientMessageId unique per (conversation, sender)
                @UniqueConstraint(
                        name = "uk_message_client_id",
                        columnNames = {"conversation_id", "sender_id", "client_message_id"}
                )
        }
)

public class MessageEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "conversation_id", nullable = false, updatable = false)
    private UUID conversationId;

    @Column(name = "sender_id", nullable = false, updatable = false)
    private String senderId;

    @Column(name = "recipient_id", nullable = false, updatable = false)
    private String recipientId;

    @Column(name = "client_message_id", nullable = false, updatable = false)
    private String clientMessageId;

    @Column(name = "text", nullable = false, length = 2000)
    private String text;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public MessageEntity() { }

    public MessageEntity(UUID id,
                         UUID conversationId,
                         String senderId,
                         String recipientId,
                         String clientMessageId,
                         String text,
                         Instant createdAt) {
        this.id = id;
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.clientMessageId = clientMessageId;
        this.text = text;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public UUID getConversationId() { return conversationId; }
    public String getSenderId() { return senderId; }
    public String getRecipientId() { return recipientId; }
    public String getClientMessageId() { return clientMessageId; }
    public String getText() { return text; }
    public Instant getCreatedAt() { return createdAt; }
}
