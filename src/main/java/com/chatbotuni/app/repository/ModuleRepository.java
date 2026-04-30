package com.chatbotuni.app.repository;

import com.chatbotuni.app.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModuleRepository extends JpaRepository<Module, Long> {

    // exact code match (SET08101)
    Optional<Module> findByCodeIgnoreCase(String code);

    // partial name match (web development)
    Optional<Module> findByNameContainingIgnoreCase(String name);

    // for URL: /modules/web-development
    Optional<Module> findBySlug(String slug);

    // exact name match (more precise chatbot)
    Optional<Module> findByNameIgnoreCase(String name);
}