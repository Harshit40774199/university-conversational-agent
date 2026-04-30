package com.chatbotuni.app.controller;

import com.chatbotuni.app.dto.ChatRequest;
import com.chatbotuni.app.dto.ChatResponse;
import com.chatbotuni.app.service.ChatbotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ChatController {

    private final ChatbotService chatbotService;

    public ChatController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        try {
            String sender = request.getUserId() != null && !request.getUserId().isBlank()
                    ? request.getUserId()
                    : "default_user";

            String message = request.getMessage() != null ? request.getMessage() : "";

            ChatResponse response = chatbotService.reply(sender, message);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ChatResponse("Sorry, I could not process your message right now."));
        }
    }
}