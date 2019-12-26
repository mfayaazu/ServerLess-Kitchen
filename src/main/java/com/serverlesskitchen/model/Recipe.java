package com.serverlesskitchen.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import java.util.List;



@Document(collection="Recipe")
public class Recipe {
  @Transient
  public static final String SEQUENCE_NAME = "users_sequence";

  @Id
  private int id;
  private String name;
  private String instructions;
  private List<Ingredients> ingredientsList;

  public Recipe(int id, String name, String instructions, List<Ingredients> ingredientsList) {
    this.id = id;
    this.name = name;
    this.instructions = instructions;
    this.ingredientsList = ingredientsList;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getInstructions() {
    return instructions;
  }

  public List<Ingredients> getIngredientsList() {
    return ingredientsList;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  public void setIngredientsList(List<Ingredients> ingredientsList) {
    this.ingredientsList = ingredientsList;
  }

  @Override
  public String toString() {
    return "Recipe{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", instructions='" + instructions + '\'' +
            ", ingredientsList=" + ingredientsList +
            '}';
  }
}
