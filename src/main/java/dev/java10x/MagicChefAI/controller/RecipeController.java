package dev.java10x.MagicChefAI.controller;

import dev.java10x.MagicChefAI.service.ChatGptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
public class RecipeController {

    private ChatGptService chatGptService;

    public RecipeController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    public Mono<ResponseEntity<String>> generateRecipe(int id) {
        return chatGptService.generateRecipe();
    }
}
