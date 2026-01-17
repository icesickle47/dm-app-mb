package com.mak.dm_api.service;

import com.mak.dm_api.persistence.ConversationEntity;
import com.mak.dm_api.persistence.ConversationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class DMConversationService {

    private final ConversationRepository conversationRepository;

    public DMConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Transactional
    public ConversationEntity getOrCreate(String userA, String userB) {
        // Canonical ordering so (A,B) and (B,A) map to same row
        String user1 = userA.compareTo(userB) <= 0 ? userA : userB;
        String user2 = userA.compareTo(userB) <= 0 ? userB : userA;

        return conversationRepository.findByUser1IdAndUser2Id(user1, user2)
                .orElseGet(() -> conversationRepository.save(
                        new ConversationEntity(UUID.randomUUID(), user1, user2, Instant.now())
                ));
    }
}
