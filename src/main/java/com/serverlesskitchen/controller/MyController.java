package com.serverlesskitchen.controller;

import com.serverlesskitchen.model.Ingredients;
import com.serverlesskitchen.model.Recipe;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.serverlesskitchen.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@Component
public class MyController {


    @Autowired
    private KitchenRepository repository;


    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    //  @GetMapping("/Recipe")
//  public Recipe showRecipe() {
//    Recipe recipes = new Recipe();
//    List<Ingredients> ingredientsList = Arrays.asList(
//        new Ingredients("Sugar", 4),
//        new Ingredients("Cake Mix", 8)
//    );
//    recipes.setId(1);
//    recipes.setName("Cake");
//    recipes.setInstructions("Bake Cake");
//    recipes.setIngredientsList(ingredientsList);
//    return recipes;
//  }

    @PostMapping("/addRecipe")
    public String saveRecipe(@RequestBody Recipe recipe) {
        repository.save(recipe);
        return "Added recipe with ID: " + recipe.getId();
    }

    @GetMapping("/findAllRecipe")
    public List<Recipe> getRecipe() {
        return repository.findAll();
    }

    @GetMapping("/findRecipeById/{id}")
    public Optional<Recipe> getRecipeById(@PathVariable int id) {
        return repository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable int id) {
        repository.deleteById(id);
        return "Recipe delete with id: " + id;
    }


}