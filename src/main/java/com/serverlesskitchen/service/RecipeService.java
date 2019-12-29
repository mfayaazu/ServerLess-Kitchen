package com.serverlesskitchen.service;

import com.serverlesskitchen.model.Recipe;
import com.serverlesskitchen.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    @Autowired
    private KitchenRepository repository;

    public Recipe update(int id,String name){
        Recipe recipe= repository.findByKitchenId(id);
        recipe.setName(name);
        return repository.save(recipe);
    }



}
