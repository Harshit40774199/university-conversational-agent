package com.chatbotuni.app.service;

import com.chatbotuni.app.dto.ChatResponse;
import com.chatbotuni.app.dto.RasaMessage;
import com.chatbotuni.app.model.Module;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatbotService {

    private final ModuleService moduleService;
    private final LlmService llmService;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${app.rasa.url:http://localhost:5005}")
    private String rasaBaseUrl;

    public ChatbotService(ModuleService moduleService, LlmService llmService) {
        this.moduleService = moduleService;
        this.llmService = llmService;
    }

    public ChatResponse reply(String sender, String message) {
        String msg = message == null ? "" : message.toLowerCase().trim();

        if (isAllModulesDeadlineQuestion(msg)) {
            return new ChatResponse(buildAllDeadlinesReply());
        }

        if (isAllModulesCreditsQuestion(msg)) {
            return new ChatResponse(buildAllCreditsReply());
        }

        if (isAllModulesLecturersQuestion(msg)) {
            return new ChatResponse(buildAllLecturersReply());
        }

        if (isAllModulesBooksQuestion(msg)) {
            return new ChatResponse(buildAllBooksReply());
        }

        if (isAllModulesAssessmentsQuestion(msg)) {
            return new ChatResponse(buildAllAssessmentsReply());
        }

        if (isAllModulesTimetablesQuestion(msg)) {
            return new ChatResponse(buildAllTimetablesReply());
        }

        if (isAllModuleCodesQuestion(msg)) {
            return new ChatResponse(buildAllModuleCodesReply());
        }

        if (isAllModulesListQuestion(msg)) {
            return new ChatResponse(buildAllModulesReply());
        }

        Optional<Module> moduleOpt = moduleService.findByMessage(message);
        if (moduleOpt.isPresent()) {
            Module module = moduleOpt.get();
            String rasaReply = getRasaReply(sender, message);
            return new ChatResponse(buildModuleReply(module, message, rasaReply));
        }

        String rasaReply = getRasaReply(sender, message);
        if (isMeaningfulRasaReply(rasaReply)) {
            return new ChatResponse(rasaReply);
        }

        String llmReply = llmService.answerWithSource(
                message,
                "University module and support context",
                buildLlmContextSummary(),
                "/"
        );

        return new ChatResponse(llmReply);
    }

    private String getRasaReply(String sender, String message) {
        String rasaUrl = rasaBaseUrl + "/webhooks/rest/webhook";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> payload = new HashMap<>();
        payload.put("sender", sender != null && !sender.isBlank() ? sender : "default_user");
        payload.put("message", message == null ? "" : message);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<RasaMessage[]> response =
                    restTemplate.postForEntity(rasaUrl, entity, RasaMessage[].class);

            RasaMessage[] messages = response.getBody();

            if (messages != null && messages.length > 0 && messages[0].getText() != null) {
                return messages[0].getText();
            }
        } catch (Exception e) {
            return "Rasa service is not reachable right now.";
        }

        return "I understood your question, but I could not find a detailed response.";
    }

    private boolean isMeaningfulRasaReply(String rasaReply) {
        if (rasaReply == null || rasaReply.isBlank()) {
            return false;
        }

        return !rasaReply.equalsIgnoreCase("I understood your question, but I could not find a detailed response.")
                && !rasaReply.equalsIgnoreCase("Rasa service is not reachable right now.");
    }

    private String buildModuleReply(Module module, String userMessage, String rasaReply) {
        String msg = userMessage == null ? "" : userMessage.toLowerCase();

        if (containsAny(msg, "credit", "credits")) {
            return module.getName() + " is worth " + nullSafe(module.getCredits()) + " credits.";
        }

        if (containsAny(msg, "lecturer", "lecturers", "teacher", "teachers", "teach", "teaches")) {
            return module.getName() + " is taught by " + nullSafe(module.getLecturer()) + ".";
        }

        if (containsAny(msg, "deadline", "deadlines", "due", "due date", "due dates")) {
            return "The deadline for " + module.getName() + " is " + nullSafe(module.getDeadline()) + ".";
        }

        if (containsAny(msg, "book", "books", "textbook", "textbooks", "reading")) {
            return "The recommended book for " + module.getName() + " is " + nullSafe(module.getBook()) + ".";
        }

        if (containsAny(msg, "assessment", "assessments", "coursework", "exam", "exams")) {
            return "The assessment for " + module.getName() + " is: " + nullSafe(module.getAssessment()) + ".";
        }

        if (containsAny(msg, "timetable", "timetables", "schedule", "schedules", "class", "classes")) {
            return "The timetable for " + module.getName() + " is: " + nullSafe(module.getTimetable()) + ".";
        }

        if (containsAny(msg, "moodle", "vle")) {
            return "You can access the Moodle page for " + module.getName() + " here: " + nullSafe(module.getMoodleUrl());
        }

        if (containsAny(msg, "resource", "resources", "material", "materials", "notes")) {
            return "You can access the resources for " + module.getName() + " here: " + nullSafe(module.getResourceUrl());
        }

        return module.getName() + " (" + module.getCode() + ") is taught by "
                + nullSafe(module.getLecturer()) + ". It is worth " + nullSafe(module.getCredits()) + " credits.\n\n"
                + "Description: " + nullSafe(module.getDescription()) + "\n\n"
                + "Assessment: " + nullSafe(module.getAssessment()) + "\n\n"
                + "Timetable: " + nullSafe(module.getTimetable()) + "\n\n"
                + "Moodle: " + nullSafe(module.getMoodleUrl())
                + (isMeaningfulRasaReply(rasaReply) ? "\n\nGuidance: " + rasaReply : "");
    }

    private String nullSafe(Object value) {
        return value == null ? "Not available" : value.toString();
    }

    private boolean containsAny(String msg, String... words) {
        if (msg == null) {
            return false;
        }
        for (String word : words) {
            if (msg.contains(word)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsAllModulesPhrase(String msg) {
        return containsAny(msg,
                "module", "modules",
                "subject", "subjects",
                "all modules", "all subjects",
                "my modules", "my subjects",
                "each module", "each subject",
                "every module", "every subject");
    }

    private boolean isAllModulesDeadlineQuestion(String msg) {
        return containsAllModulesPhrase(msg)
                && containsAny(msg, "deadline", "deadlines", "due", "due date", "due dates");
    }

    private boolean isAllModulesCreditsQuestion(String msg) {
        return containsAllModulesPhrase(msg)
                && containsAny(msg, "credit", "credits");
    }

    private boolean isAllModulesLecturersQuestion(String msg) {
        return containsAllModulesPhrase(msg)
                && containsAny(msg, "lecturer", "lecturers", "teacher", "teachers", "teach", "teaches");
    }

    private boolean isAllModulesBooksQuestion(String msg) {
        return containsAllModulesPhrase(msg)
                && containsAny(msg, "book", "books", "textbook", "textbooks", "reading");
    }

    private boolean isAllModulesAssessmentsQuestion(String msg) {
        return containsAllModulesPhrase(msg)
                && containsAny(msg, "assessment", "assessments", "coursework", "exam", "exams");
    }

    private boolean isAllModulesTimetablesQuestion(String msg) {
        return containsAllModulesPhrase(msg)
                && containsAny(msg, "timetable", "timetables", "schedule", "schedules", "class", "classes");
    }

    private boolean isAllModuleCodesQuestion(String msg) {
        return containsAllModulesPhrase(msg)
                && containsAny(msg, "code", "codes");
    }

    private boolean isAllModulesListQuestion(String msg) {
        if (msg == null || msg.isBlank()) {
            return false;
        }

        return msg.equals("modules")
                || msg.equals("subjects")
                || containsAny(msg,
                "all modules", "all subjects",
                "list modules", "list subjects",
                "show modules", "show subjects",
                "my modules", "my subjects",
                "what modules", "what subjects");
    }

    private String buildAllDeadlinesReply() {
        List<Module> modules = moduleService.getAllModules();

        if (modules.isEmpty()) {
            return "I could not find any modules in the database.";
        }

        StringBuilder sb = new StringBuilder("Here are the deadlines for all modules:\n\n");
        for (Module module : modules) {
            sb.append("- ")
                    .append(module.getName())
                    .append(" (")
                    .append(module.getCode())
                    .append("): ")
                    .append(nullSafe(module.getDeadline()))
                    .append("\n");
        }
        return sb.toString();
    }

    private String buildAllCreditsReply() {
        List<Module> modules = moduleService.getAllModules();

        if (modules.isEmpty()) {
            return "I could not find any modules in the database.";
        }

        StringBuilder sb = new StringBuilder("Here are the credits for all modules:\n\n");
        for (Module module : modules) {
            sb.append("- ")
                    .append(module.getName())
                    .append(" (")
                    .append(module.getCode())
                    .append("): ")
                    .append(nullSafe(module.getCredits()))
                    .append(" credits\n");
        }
        return sb.toString();
    }

    private String buildAllLecturersReply() {
        List<Module> modules = moduleService.getAllModules();

        if (modules.isEmpty()) {
            return "I could not find any modules in the database.";
        }

        StringBuilder sb = new StringBuilder("Here are the lecturers for all modules:\n\n");
        for (Module module : modules) {
            sb.append("- ")
                    .append(module.getName())
                    .append(" (")
                    .append(module.getCode())
                    .append("): ")
                    .append(nullSafe(module.getLecturer()))
                    .append("\n");
        }
        return sb.toString();
    }

    private String buildAllBooksReply() {
        List<Module> modules = moduleService.getAllModules();

        if (modules.isEmpty()) {
            return "I could not find any modules in the database.";
        }

        StringBuilder sb = new StringBuilder("Here are the recommended books for all modules:\n\n");
        for (Module module : modules) {
            sb.append("- ")
                    .append(module.getName())
                    .append(" (")
                    .append(module.getCode())
                    .append("): ")
                    .append(nullSafe(module.getBook()))
                    .append("\n");
        }
        return sb.toString();
    }

    private String buildAllAssessmentsReply() {
        List<Module> modules = moduleService.getAllModules();

        if (modules.isEmpty()) {
            return "I could not find any modules in the database.";
        }

        StringBuilder sb = new StringBuilder("Here are the assessments for all modules:\n\n");
        for (Module module : modules) {
            sb.append("- ")
                    .append(module.getName())
                    .append(" (")
                    .append(module.getCode())
                    .append("): ")
                    .append(nullSafe(module.getAssessment()))
                    .append("\n");
        }
        return sb.toString();
    }

    private String buildAllTimetablesReply() {
        List<Module> modules = moduleService.getAllModules();

        if (modules.isEmpty()) {
            return "I could not find any modules in the database.";
        }

        StringBuilder sb = new StringBuilder("Here are the timetables for all modules:\n\n");
        for (Module module : modules) {
            sb.append("- ")
                    .append(module.getName())
                    .append(" (")
                    .append(module.getCode())
                    .append("): ")
                    .append(nullSafe(module.getTimetable()))
                    .append("\n");
        }
        return sb.toString();
    }

    private String buildAllModuleCodesReply() {
        List<Module> modules = moduleService.getAllModules();

        if (modules.isEmpty()) {
            return "I could not find any modules in the database.";
        }

        StringBuilder sb = new StringBuilder("Here are the module codes:\n\n");
        for (Module module : modules) {
            sb.append("- ")
                    .append(module.getName())
                    .append(": ")
                    .append(module.getCode())
                    .append("\n");
        }
        return sb.toString();
    }

    private String buildAllModulesReply() {
        List<Module> modules = moduleService.getAllModules();

        if (modules.isEmpty()) {
            return "I could not find any modules in the database.";
        }

        StringBuilder sb = new StringBuilder("Here are all available modules:\n\n");
        for (Module module : modules) {
            sb.append("- ")
                    .append(module.getName())
                    .append(" (")
                    .append(module.getCode())
                    .append(")\n");
        }
        return sb.toString();
    }

    private String buildLlmContextSummary() {
        List<Module> modules = moduleService.getAllModules();

        if (modules.isEmpty()) {
            return "No module records are available in the database.";
        }

        return modules.stream()
                .limit(12)
                .map(module -> module.getCode() + " - " + module.getName()
                        + " | lecturer: " + nullSafe(module.getLecturer())
                        + " | credits: " + nullSafe(module.getCredits())
                        + " | deadline: " + nullSafe(module.getDeadline()))
                .collect(Collectors.joining("\n"));
    }
}