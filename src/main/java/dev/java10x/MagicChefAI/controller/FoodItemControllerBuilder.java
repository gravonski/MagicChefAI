package dev.java10x.MagicChefAI.controller;

import dev.java10x.MagicChefAI.service.FoodItemService;

public class FoodItemControllerBuilder {
    private FoodItemService foodItemService;

    public FoodItemControllerBuilder setFoodItemService(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
        return this;
    }

    public FoodItemController createFoodItemController() {
        return new FoodItemController(foodItemService);
    }
}