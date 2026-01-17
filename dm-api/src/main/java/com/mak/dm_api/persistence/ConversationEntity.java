package com.mak.dm_api.persistence;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "conversations",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_conversation_user_pair",
                columnNames = {"user1_id", "user2_id"}
        )
)
public class ConversationEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "user1_id", nullable = false, updatable = false)
    private String user1Id;

    @Column(name = "user2_id", nullable = false, updatable = false)
    private String user2Id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    protected ConversationEntity() {}

    public ConversationEntity(UUID id, String user1Id, String user2Id, Instant createdAt) {
        this.id = id;
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public String getUser1Id() { return user1Id; }
    public String getUser2Id() { return user2Id; }
    public Instant getCreatedAt() { return createdAt; }
}