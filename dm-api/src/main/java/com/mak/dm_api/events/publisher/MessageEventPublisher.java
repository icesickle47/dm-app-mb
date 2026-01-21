package com.mak.dm_api.events.publisher;

import com.mak.dm_api.events.MessageCreatedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageEventPublisher {

    private final KafkaTemplate<String, MessageCreatedEvent> kafkaTemplate;
    private final String topic;

    public MessageEventPublisher(
            KafkaTemplate<String, MessageCreatedEvent> kafkaTemplate,
            @Value("${app.kafka.topics.messages-created:messages.created}") String topic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void publishMessageCreated(MessageCreatedEvent event) {
        // Key = conversationId to preserve order per conversation (important for chat!)
        String key = String.valueOf(event.getConversationId());
        kafkaTemplate.send(topic, key, event);
    }
}
