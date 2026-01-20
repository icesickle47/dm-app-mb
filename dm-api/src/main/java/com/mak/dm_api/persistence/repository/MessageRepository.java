package com.mak.dm_api.persistence.repository;

import com.mak.dm_api.persistence.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}
