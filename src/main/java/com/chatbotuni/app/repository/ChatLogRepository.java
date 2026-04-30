package com.chatbotuni.app.repository;

import com.chatbotuni.app.model.ChatLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {
}
