package com.mak.dm_api.api.controller;

import com.mak.dm_api.api.SendMessageRequest;
import com.mak.dm_api.api.SendMessageResponse;
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
    public ResponseEntity<SendMessageResponse> send(@RequestBody SendMessageRequest request) {
        MessageEntity saved = messageService.send(request);

        return ResponseEntity.ok(new SendMessageResponse(
                saved.getId(),
                saved.getConversationId(),
                saved.getSenderId(),
                saved.getRecipientId(),
                saved.getClientMessageId(),
                saved.getText(),
                saved.getCreatedAt()
        ));
    }
}
