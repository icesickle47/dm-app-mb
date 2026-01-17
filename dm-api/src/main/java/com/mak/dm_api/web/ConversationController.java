package com.mak.dm_api.web;

import com.mak.dm_api.persistence.ConversationEntity;
import com.mak.dm_api.persistence.ConversationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConversationController {

    private final ConversationRepository conversationRepository;

    public ConversationController(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @GetMapping("/conversations")
    public List<ConversationEntity> all() {
        return conversationRepository.findAll();
    }
}
