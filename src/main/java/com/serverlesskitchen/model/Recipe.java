package com.serverlesskitchen.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Recipe)) return false;
    Recipe recipe = (Recipe) o;
    return getKitchenId() == recipe.getKitchenId() &&
            getName().equals(recipe.getName()) &&
            getInstructions().equals(recipe.getInstructions()) &&
            getIngredients().equals(recipe.getIngredients());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKitchenId(), getName(), getInstructions(), getIngredients());
  }
}
