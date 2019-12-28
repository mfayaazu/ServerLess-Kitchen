package com.serverlesskitchen.controller;

import com.serverlesskitchen.model.Inventory;
import com.serverlesskitchen.model.Recipe;

import java.util.List;
import java.util.Optional;

import com.serverlesskitchen.repository.InventoryRepository;
import com.serverlesskitchen.repository.KitchenRepository;
import com.serverlesskitchen.service.NextSequenceService;
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
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private NextSequenceService nextSequenceService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/recipes/create")
    public String saveRecipe(@RequestBody Recipe recipe) {
        recipe.setId(nextSequenceService.getNextSequence("CustomSequences"));
        repository.save(recipe);
            return "Added recipe with ID: " + recipe.getId();
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
        int finalQuantity;
        if (inventory.getQuantity() > 0) {
            existingQuantity=findExistingQuantity(inventory.getName());
            finalQuantity = existingQuantity +requestedQuantity;
            inventory.setQuantity(finalQuantity);
            inventoryRepository.save(inventory);
        } else {
            return "Invalid Data Please Provide a valid data";
        }
        return "Inventory filled with " + inventory.getName() + "Quantity :" + finalQuantity;
    }

    public int findExistingQuantity(String name){
        Optional<Inventory> getQuantity = inventoryRepository.findById(name);
        int quantity = getQuantity.get().getQuantity();
        return quantity;
    }
}