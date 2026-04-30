package com.chatbotuni.app.controller;

import com.chatbotuni.app.model.KnowledgeSource;
import com.chatbotuni.app.service.KnowledgeSourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/knowledge-sources")
@CrossOrigin(origins = "*")
public class KnowledgeSourceController {

    private final KnowledgeSourceService knowledgeSourceService;

    public KnowledgeSourceController(KnowledgeSourceService knowledgeSourceService) {
        this.knowledgeSourceService = knowledgeSourceService;
    }

    @GetMapping
    public List<KnowledgeSource> getKnowledgeSources(@RequestParam(required = false) String category) {
        if (category == null || category.isBlank()) {
            return knowledgeSourceService.getAllSources();
        }
        return knowledgeSourceService.getByCategory(category);
    }
}
