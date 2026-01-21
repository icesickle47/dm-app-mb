package com.mak.dm_api.service;

import com.mak.dm_api.api.SendMessageRequest;
import com.mak.dm_api.persistence.ConversationEntity;
import com.mak.dm_api.persistence.MessageEntity;
import com.mak.dm_api.persistence.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class MessageService {

    private final ConversationService conversationService;
    private final MessageRepository messageRepository;

    public MessageService(ConversationService conversationService, MessageRepository messageRepository) {
        this.conversationService = conversationService;
        this.messageRepository = messageRepository;
    }

    @Transactional
    public MessageEntity send(SendMessageRequest req) {
        ConversationEntity convo = conversationService.getOrCreate(req.senderId(), req.recipientId());

        return messageRepository
                .findByConversationIdAndSenderIdAndClientMessageId(convo.getId(), req.senderId(), req.clientMessageId())
                .orElseGet(() -> messageRepository.save(
                        new MessageEntity(
                                UUID.randomUUID(),
                                convo.getId(),
                                req.senderId(),
                                req.recipientId(),
                                req.clientMessageId(),
                                req.text(),
                                Instant.now()
                        )
                ));
    }
}
