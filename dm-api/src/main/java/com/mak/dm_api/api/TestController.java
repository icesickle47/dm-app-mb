package com.mak.dmapi.api;

import com.mak.contracts.MessageCreatedEvent;
import com.mak.dmapi.kafka.MessageEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/test")
public class TestController {

    private final MessageEventPublisher publisher;

    public TestController(MessageEventPublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping("/publish")
    public String publish(@RequestParam String senderId,
                          @RequestParam String recipientId,
                          @RequestParam String conversationId,
                          @RequestParam String text) {

        var event = new MessageCreatedEvent(
                UUID.randomUUID().toString(),
                conversationId,
                senderId,
                recipientId,
                text,
                System.currentTimeMillis()
        );

        publisher.publish(event);
        return "published " + event.messageId();
    }
}
