package com.serverlesskitchen.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "serverless_kitchen", name = "Recipes")
public class Recipe {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String name;
  private String instructions;
  @JsonProperty("ingredientsList")
  private List<Ingredients> ingredientsList;

  public Recipe() {

  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getInstructions() {
    return instructions;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  public List<Ingredients> getIngredientsList() {
    return ingredientsList;
  }

  public void setIngredientsList(List<Ingredients> ingredientsList) {
    this.ingredientsList = ingredientsList;
  }
}
