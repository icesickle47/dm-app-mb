package com.mak.dm_api.service;

import com.mak.dm_api.persistence.ConversationEntity;
import com.mak.dm_api.persistence.ConversationRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class ConversationService {

    private final ConversationRepository repo;

    public ConversationService(ConversationRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public ConversationEntity getOrCreate(String userA, String userB) {
        UserPair pair = UserPair.canonical(userA, userB);

        return repo.findByUser1IdAndUser2Id(pair.user1(), pair.user2())
                .orElseGet(() -> {
                    try {
                        ConversationEntity c = new ConversationEntity(
                                UUID.randomUUID(),
                                pair.user1(),
                                pair.user2(),
                                Instant.now()
                        );
                        return repo.save(c);
                    } catch (DataIntegrityViolationException race) {
                        // If two requests create at same time, unique constraint wins.
                        return repo.findByUser1IdAndUser2Id(pair.user1(), pair.user2())
                                .orElseThrow(() -> race);
                    }
                });
    }
}
