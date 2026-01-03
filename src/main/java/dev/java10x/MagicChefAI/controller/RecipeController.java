package dev.java10x.MagicChefAI.controller;

import dev.java10x.MagicChefAI.model.FoodItem;
import dev.java10x.MagicChefAI.service.ChatGptService;
import dev.java10x.MagicChefAI.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
public class RecipeController {

    private FoodItemService foodItemService;
    private ChatGptService chatGptService;

    public RecipeController(ChatGptService chatGptService, FoodItemService foodItemService) {
        this.chatGptService = chatGptService;
        this.foodItemService = foodItemService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe(Long id) {
        List<FoodItem> foodItems = foodItemService.listar();
        return chatGptService.generateRecipe(foodItems)
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
