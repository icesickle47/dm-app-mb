package com.mak.dm_api.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {

    Optional<MessageEntity> findByConversationIdAndSenderIdAndClientMessageId(UUID conversationId, String senderId, String clientMessageId);

    List<MessageEntity> findByConversationIdAndCreatedAtLessThanOrderByCreatedAtDesc(UUID conversationId, Instant before, Pageable pageable);

    List<MessageEntity> findByConversationIdOrderByCreatedAtDesc(UUID conversationId, Pageable pageable);
}
