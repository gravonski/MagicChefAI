package dev.java10x.MagicChefAI.controller;

import dev.java10x.MagicChefAI.model.FoodItem;
import dev.java10x.MagicChefAI.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService service;


    public FoodItemController(FoodItemService service) {
        this.service = service;
    }

    public ResponseEntity<FoodItem> criar (@RequestBody FoodItem foodItem) {
        FoodItem salvo = service.salvar(foodItem);
        return ResponseEntity.ok(salvo);
    }

}
