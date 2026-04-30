package com.chatbotuni.app.service;

import com.chatbotuni.app.dto.RasaMessage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RasaService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String rasaUrl = "http://localhost:5005/webhooks/rest/webhook";

    public String getReply(String sender, String message) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("sender", sender == null || sender.isBlank() ? "student1" : sender);
            requestBody.put("message", message == null ? "" : message);

            HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<RasaMessage[]> response =
                    restTemplate.postForEntity(rasaUrl, entity, RasaMessage[].class);

            RasaMessage[] body = response.getBody();

            if (body != null && body.length > 0 && body[0].getText() != null) {
                return body[0].getText();
            }

        } catch (Exception e) {
            e.printStackTrace(); 
        }

        return "Uni Assist is ready, but the Rasa service is not available right now. I will still try to answer from the database and saved university sources.";
    }
}