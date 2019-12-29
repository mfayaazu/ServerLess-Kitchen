package com.serverlesskitchen.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection="Recipe")
public class Recipe {

  @Id
  private int kitchenId;
  private String name;
  private String instructions;
  private List<Ingredients> ingredients;

  public int getKitchenId() {
    return kitchenId;
  }

  public String getName() {
    return name;
  }

  public String getInstructions() {
    return instructions;
  }

  public List<Ingredients> getIngredients() {
    return ingredients;
  }

  public void setKitchenId(int kitchenId) {
    this.kitchenId = kitchenId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  public void setIngredients(List<Ingredients> ingredients) {
    this.ingredients = ingredients;
  }

  @Override
  public String toString() {
    return "Recipe{" +
            "id=" + kitchenId +
            ", name='" + name + '\'' +
            ", instructions='" + instructions + '\'' +
            ", ingredientsList=" + ingredients +
            '}';
  }
}
