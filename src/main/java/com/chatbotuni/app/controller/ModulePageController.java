package com.chatbotuni.app.controller;

import com.chatbotuni.app.model.Module;
import com.chatbotuni.app.service.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/modules")
public class ModulePageController {

    private final ModuleService moduleService;

    public ModulePageController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping("/{slug}")
    public String modulePage(@PathVariable String slug, Model model) {
        Module module = moduleService.findBySlug(slug).orElse(null);

        if (module == null) {
            return "module-not-found";
        }

        model.addAttribute("module", module);
        return "module-details";
    }
}