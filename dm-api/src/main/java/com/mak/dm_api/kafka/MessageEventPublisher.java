package com.mak.dmapi.kafka;

import com.mak.contracts.MessageCreatedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageEventPublisher {

    private final KafkaTemplate<String, MessageCreatedEvent> kafkaTemplate;
    private final String topic;

    public MessageEventPublisher(
            KafkaTemplate<String, MessageCreatedEvent> kafkaTemplate,
            @Value("${app.kafka.topics.messageCreated}") String topic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void publish(MessageCreatedEvent event) {
        kafkaTemplate.send(topic, event.conversationId(), event);
    }
}