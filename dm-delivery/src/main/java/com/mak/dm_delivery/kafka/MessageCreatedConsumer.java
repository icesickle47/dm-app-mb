package com.mak.dmdelivery.kafka;

import com.mak.contracts.MessageCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class MessageCreatedConsumer {

    @KafkaListener(topics = "${app.kafka.topics.messageCreated}")
    public void onMessageCreated(
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            MessageCreatedEvent event
    ) {
        System.out.println("Consumed key=" + key + " event=" + event);
    }
}
