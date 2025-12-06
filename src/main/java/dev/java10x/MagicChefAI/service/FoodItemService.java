package dev.java10x.MagicChefAI.service;

import dev.java10x.MagicChefAI.model.FoodItem;
import dev.java10x.MagicChefAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    private FoodItemRepository repository;

    public FoodItemService(FoodItemRepository repository) {
        this.repository = repository;
    }

    public FoodItem salvar(FoodItem foodItem) {
        return repository.save(foodItem);
    }

    public List<FoodItem> listar() {
        return repository.findAll();
    }


    /*fazer os métodos
    * listar por id
    * alterar
    * deletar
    * */
}


