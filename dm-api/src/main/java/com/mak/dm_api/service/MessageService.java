package com.mak.dm_api.service;

import com.mak.dm_api.api.dto.CreateMessageRequest;
import com.mak.dm_api.events.MessageCreatedEvent;
import com.mak.dm_api.events.publisher.MessageEventPublisher;
import com.mak.dm_api.persistence.ConversationEntity;
import com.mak.dm_api.persistence.MessageEntity;
import com.mak.dm_api.persistence.repository.ConversationRepository;
import com.mak.dm_api.persistence.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class MessageService {

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final MessageEventPublisher publisher;

    public MessageService(ConversationRepository conversationRepository,
                          MessageRepository messageRepository,
                          MessageEventPublisher publisher) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.publisher = publisher;
    }

    @Transactional
    public MessageEntity createMessage(Long conversationId, CreateMessageRequest req) {
        ConversationEntity conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new IllegalArgumentException("Conversation not found: " + conversationId));

        // Build MessageEntity based on your actual fields
        MessageEntity message = new MessageEntity();
        // message.setConversation(conversation) OR message.setConversationId(conversationId) depending on your entity
        // message.setSenderUserId(req.getSenderUserId())
        // message.setContent(req.getContent())
        // message.setClientMessageId(req.getClientMessageId())
        // message.setCreatedAt(OffsetDateTime.now())

        // IMPORTANT: you must map fields to match your MessageEntity.java
        // We'll adjust this once you confirm the entity fields.

        MessageEntity saved = messageRepository.save(message);

        MessageCreatedEvent event = new MessageCreatedEvent(
                saved.getId(),
                conversationId,
                req.getSenderUserId(),
                req.getContent(),
                OffsetDateTime.now()
        );

        publisher.publishMessageCreated(event);

        return saved;
    }
}
