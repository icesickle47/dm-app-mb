package com.mak.dm_api.service;

import com.mak.dm_api.api.SendMessageRequest;
import com.mak.dm_api.persistence.MessageEntity;
import com.mak.dm_api.persistence.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public MessageEntity send(SendMessageRequest req) {
        // Idempotency: if client retries same message, return the original row
        return messageRepository
                .findByConversationIdAndSenderIdAndClientMessageId(
                        req.conversationId(),
                        req.senderId(),
                        req.clientMessageId()
                )
                .orElseGet(() -> {
                    MessageEntity entity = new MessageEntity(
                            UUID.randomUUID(),
                            req.conversationId(),
                            req.senderId(),
                            req.recipientId(),
                            req.clientMessageId(),
                            req.text(),
                            Instant.now()
                    );
                    return messageRepository.save(entity);
                });
    }
}
