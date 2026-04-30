package com.chatbotuni.app.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class LlmService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${app.llm.enabled:false}")
    private boolean enabled;

    @Value("${app.llm.url:}")
    private String llmUrl;

    @Value("${app.llm.api-key:}")
    private String apiKey;

    @Value("${app.llm.model:gpt-4o-mini}")
    private String model;

    public boolean isConfigured() {
        return enabled && llmUrl != null && !llmUrl.isBlank() && apiKey != null && !apiKey.isBlank();
    }

    public String answerWithSource(String userMessage, String sourceTitle, String sourceSummary, String sourceUrl) {
        if (!isConfigured()) {
            return "I found the most relevant source in " + sourceTitle + ". "
                    + sourceSummary + " You can open it here: " + sourceUrl;
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            String prompt = String.format(
                    "Student question: %s\n\nSource title: %s\nSource summary: %s\nSource URL: %s",
                    userMessage,
                    sourceTitle,
                    sourceSummary,
                    sourceUrl
            );

            Map<String, Object> body = Map.of(
                    "model", model,
                    "messages", List.of(
                            Map.of("role", "system", "content", "You are a university support assistant. Answer only from the provided source summary and mention the URL at the end."),
                            Map.of("role", "user", "content", prompt)
                    ),
                    "temperature", 0.2
            );

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<JsonNode> response = restTemplate.postForEntity(llmUrl, entity, JsonNode.class);
            JsonNode root = response.getBody();
            if (root != null && root.has("choices") && root.get("choices").isArray() && root.get("choices").size() > 0) {
                JsonNode content = root.get("choices").get(0).path("message").path("content");
                if (!content.isMissingNode() && !content.asText().isBlank()) {
                    return content.asText();
                }
            }
        } catch (Exception ignored) {
        }

        return "I found the most relevant source in " + sourceTitle + ". "
                + sourceSummary + " You can open it here: " + sourceUrl;
    }
}
