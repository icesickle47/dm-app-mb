package com.mak.dm_api.persistence.repository;

import com.mak.dm_api.persistence.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<ConversationEntity, Long> {
}
