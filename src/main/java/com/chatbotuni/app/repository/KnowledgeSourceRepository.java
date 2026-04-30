package com.chatbotuni.app.repository;

import com.chatbotuni.app.model.KnowledgeSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KnowledgeSourceRepository extends JpaRepository<KnowledgeSource, Long> {
    List<KnowledgeSource> findByCategoryIgnoreCase(String category);
}
