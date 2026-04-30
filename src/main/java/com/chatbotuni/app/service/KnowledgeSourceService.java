package com.chatbotuni.app.service;

import com.chatbotuni.app.model.KnowledgeSource;
import com.chatbotuni.app.repository.KnowledgeSourceRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class KnowledgeSourceService {

    private final KnowledgeSourceRepository knowledgeSourceRepository;

    public KnowledgeSourceService(KnowledgeSourceRepository knowledgeSourceRepository) {
        this.knowledgeSourceRepository = knowledgeSourceRepository;
    }

    public List<KnowledgeSource> getAllSources() {
        return knowledgeSourceRepository.findAll();
    }

    public List<KnowledgeSource> getByCategory(String category) {
        return knowledgeSourceRepository.findByCategoryIgnoreCase(category);
    }

    public Optional<KnowledgeSource> findBestMatch(String message) {
        if (message == null || message.isBlank()) {
            return Optional.empty();
        }

        String normalized = message.toLowerCase(Locale.ROOT);

        return knowledgeSourceRepository.findAll().stream()
                .max(Comparator.comparingInt(source -> score(source, normalized)))
                .filter(source -> score(source, normalized) > 0);
    }

    private int score(KnowledgeSource source, String message) {
        int score = 0;
        if (message.contains(source.getCategory().toLowerCase(Locale.ROOT))) {
            score += 4;
        }
        for (String keyword : source.getKeywords().split(",")) {
            String trimmed = keyword.trim().toLowerCase(Locale.ROOT);
            if (!trimmed.isBlank() && message.contains(trimmed)) {
                score += 2;
            }
        }
        if (message.contains(source.getTitle().toLowerCase(Locale.ROOT))) {
            score += 3;
        }
        return score;
    }
}
