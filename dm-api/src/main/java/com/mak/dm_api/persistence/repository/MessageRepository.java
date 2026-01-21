package com.mak.dm_api.persistence.repository;

import com.mak.dm_api.persistence.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//package com.mak.dm_api.persistence; // here GPT is suggesting a different location of this file, I've made the decision to keep this file where it is.


import java.util.Optional;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {

    Optional<MessageEntity> findByConversationIdAndSenderIdAndClientMessageId(
            UUID conversationId,
            String senderId,
            String clientMessageId
    );
}
