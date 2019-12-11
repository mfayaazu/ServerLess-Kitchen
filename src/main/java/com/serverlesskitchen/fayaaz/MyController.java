package com.serverlesskitchen.fayaaz;

import com.serverlesskitchen.model.Ingredients;
import com.serverlesskitchen.model.Recipe;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

  private Service service;

  @GetMapping("/ping")
  public String ping() {

    return "pong";
  }

  @GetMapping("/Recipe")
  public Recipe showRecipe() {
    Recipe recipes = new Recipe();
    List<Ingredients> ingredientsList = Arrays.asList(
        new Ingredients("Sugar", 4),
        new Ingredients("Cake Mix", 8)
    );
    recipes.setId(1);
    recipes.setName("Cake");
    recipes.setInstructions("Bake Cake");
    recipes.setIngredientsList(ingredientsList);
    return recipes;
  }
  

}