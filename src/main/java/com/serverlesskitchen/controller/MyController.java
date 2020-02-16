package com.serverlesskitchen.controller;

import com.serverlesskitchen.model.*;
import com.serverlesskitchen.repository.InventoryRepository;
import com.serverlesskitchen.repository.KitchenRepository;
import com.serverlesskitchen.repository.SequenceRepository;
import com.serverlesskitchen.service.NextSequenceService;
import com.serverlesskitchen.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@Component
public class MyController {

    public boolean status = true;
    @Autowired
    private KitchenRepository repository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private NextSequenceService nextSequenceService;
    @Autowired
    private SequenceRepository sequenceRepository;
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/recipes/create")
    public String saveRecipe(@RequestBody Recipe recipe) {
        recipe.setKitchenId(nextSequenceService.getNextSequence("CustomSequences"));
        repository.save(recipe);
        return "Added recipe with ID: " + recipe.getKitchenId();
    }

    @GetMapping("/recipes")
    public List<Recipe> getRecipe() {
        return repository.findAll();
    }

    @GetMapping("/recipes/{id}")
    public Optional<Recipe> getRecipeById(@PathVariable int id) {
        return repository.findById(id);
    }

    @DeleteMapping("/clear")
    public String deleteAllRecipe() {
        repository.deleteAll();
        inventoryRepository.deleteAll();
        sequenceRepository.deleteAll();
        return "All Recipes and inventory cleared";
    }

    @DeleteMapping("/recipes/{id}")
    public String deleteRecipe(@PathVariable int id) {
        repository.deleteById(id);
        return "Recipe delete with id: " + id;
    }

    @GetMapping("/inventory")
    public List<Inventory> getInventoryList() {
        return inventoryRepository.findAll();
    }

    @PostMapping("/inventory/fill")
    public String fillInventory(@RequestBody Inventory inventory) {
        int requestedQuantity = inventory.getQuantity();
        int existingQuantity;
        int finalQuantity = 0;
        if (inventory.getQuantity() > 0) {
            existingQuantity = findExistingQuantity(inventory.getName());
            if (status == true) {
                finalQuantity = existingQuantity + requestedQuantity;
                inventory.setQuantity(finalQuantity);
            } else {
                finalQuantity = inventory.getQuantity();
                inventory.setQuantity(inventory.getQuantity());
            }
            inventoryRepository.save(inventory);
            return "Inventory filled with " + inventory.getName() + "Quantity :" + finalQuantity;
        } else {
            return "Invalid Quantity";
        }
    }


    @PatchMapping("/recipes/{id}")
    public ResponseEntity<String> updateExistingRecipe(@RequestBody Recipe recipe, @PathVariable("id") int id) {
        recipeService.update(id, recipe.getName());
        return ResponseEntity.ok("updated");

    }

    @GetMapping("/recipes/get-count-by-recipe")
    public List<RecipeCount> getCountByRecipe() {
        Wastage wastagearg = new Wastage();
        return countNumberOfRecipeToMake(wastagearg);
    }

    @GetMapping("/recipes/optimize-total-waste")
    public List<Wastage> calculateWastage() {
        int recipeCountValue;
        int finalCountValue = 0;
        List<Wastage> wastageList = new ArrayList<>();
        Wastage wastageObj = new Wastage();
        List<RecipeCount> fetchedCountList = countNumberOfRecipeToMake(wastageObj);
        for (RecipeCount recipeCount : fetchedCountList
        ) {
            recipeCountValue = recipeCount.getCount();
            finalCountValue = recipeCountValue + finalCountValue;
        }
        wastageObj.setRecipes(fetchedCountList);
        wastageObj.setRecipeCount(finalCountValue);
        wastageObj.getUnusedInventoryCount();
        wastageList.add(wastageObj);
        return wastageList;
    }

    public List<RecipeCount> countNumberOfRecipeToMake(Wastage wastage) {
        int counter;
        int reduceQuantity;
        int finalCounter = 0;
        int unusedQuantityLeft = 0;
        int wastageQuantity = 0;
        List<Recipe> fetchAllRecipe = repository.findAll();
        List<RecipeCount> recipeMessage = new ArrayList<>();
        for (Recipe i : fetchAllRecipe) {
            RecipeCount recipeCounter = new RecipeCount();
            recipeCounter.setId(i.getKitchenId());
            for (Ingredients ingredients : i.getIngredients()
            ) {
                counter = 0;
                List<Inventory> inventoryList = inventoryRepository.findAll();
                for (Inventory j : inventoryList
                ) {
                    reduceQuantity = j.getQuantity();
                    while ((ingredients.getName().equals(j.getName())) && (reduceQuantity >= ingredients.getQuantity())) {
                        reduceQuantity = j.getQuantity() - ingredients.getQuantity();
                        j.setQuantity(reduceQuantity);
                        wastageQuantity = reduceQuantity;
                        counter++;
                    }
                    if (finalCounter == 0) {
                        finalCounter = counter;
                    }
                }
                unusedQuantityLeft = wastageQuantity + unusedQuantityLeft;
                if (finalCounter > counter) {
                    recipeCounter.setCount(counter);
                } else {
                    recipeCounter.setCount(finalCounter);
                }
            }
            wastage.setUnusedInventoryCount(unusedQuantityLeft);
            recipeMessage.add(recipeCounter);
            finalCounter = 0;
        }
        return recipeMessage;
    }


    public int findExistingQuantity(String name) {
        try {
            Optional<Inventory> existingQuantity = inventoryRepository.findById(name);
            int quantity = existingQuantity.get().getQuantity();
            return quantity;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}