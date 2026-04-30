package com.chatbotuni.app.service;

import com.chatbotuni.app.model.Module;
import com.chatbotuni.app.repository.ModuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    private final ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Optional<Module> findBySlug(String slug) {
        if (slug == null || slug.isBlank()) {
            return Optional.empty();
        }
        return moduleRepository.findBySlug(slug.trim());
    }

    public Optional<Module> findByCode(String code) {
        if (code == null || code.isBlank()) {
            return Optional.empty();
        }
        return moduleRepository.findByCodeIgnoreCase(code.trim());
    }

    public Optional<Module> findByName(String name) {
        if (name == null || name.isBlank()) {
            return Optional.empty();
        }

        String cleaned = name.trim();

        Optional<Module> exactMatch = moduleRepository.findByNameIgnoreCase(cleaned);
        if (exactMatch.isPresent()) {
            return exactMatch;
        }

        return moduleRepository.findByNameContainingIgnoreCase(cleaned);
    }

    public Optional<Module> findByMessage(String message) {
        if (message == null || message.isBlank()) {
            return Optional.empty();
        }

        String normalized = normalizeText(message);

        Optional<Module> byCode = moduleRepository.findByCodeIgnoreCase(normalized);
        if (byCode.isPresent()) {
            return byCode;
        }

        Optional<Module> byName = moduleRepository.findByNameIgnoreCase(normalized);
        if (byName.isPresent()) {
            return byName;
        }

        Optional<Module> partialByName = moduleRepository.findByNameContainingIgnoreCase(normalized);
        if (partialByName.isPresent()) {
            return partialByName;
        }

        return moduleRepository.findAll().stream()
                .filter(module -> {
                    String code = normalizeText(module.getCode());
                    String name = normalizeText(module.getName());
                    String slug = normalizeText(module.getSlug());

                    return normalized.contains(code)
                            || normalized.contains(name)
                            || (!slug.isBlank() && normalized.contains(slug))
                            || name.contains(normalized)
                            || (!slug.isBlank() && slug.contains(normalized))
                            || normalized.replace(" module", "").contains(name)
                            || normalized.replace(" class", "").contains(name)
                            || normalized.replace(" subject", "").contains(name);
                })
                .findFirst();
    }

    private String normalizeText(String text) {
        if (text == null) {
            return "";
        }
        return text.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }
}