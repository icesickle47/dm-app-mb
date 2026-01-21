package com.mak.dm_api.api.controller;

import com.mak.dm_api.api.dto.CreateMessageRequest;
import com.mak.dm_api.api.dto.MessageResponse;
import com.mak.dm_api.persistence.MessageEntity;
import com.mak.dm_api.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/conversations")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/{conversationId}/messages")
    public ResponseEntity<MessageResponse> createMessage(
            @PathVariable Long conversationId,
            @RequestBody CreateMessageRequest request
    ) {
        MessageEntity saved = messageService.createMessage(conversationId, request);

        // Adjust getters based on your entity
        MessageResponse response = new MessageResponse(
                saved.getId(),
                conversationId,
                request.getSenderUserId(),
                request.getContent(),
                null // replace with saved.getCreatedAt() if you have it
        );

        return ResponseEntity
                .created(URI.create("/conversations/" + conversationId + "/messages/" + saved.getId()))
                .body(response);
    }
}
