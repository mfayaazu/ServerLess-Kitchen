package com.serverlesskitchen.model;

import java.util.List;

public class Wastage {

    private List<RecipeCount> recipes;
    private int recipeCount;
    private int unusedInventoryCount;

    public List<RecipeCount> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeCount> recipes) {
        this.recipes = recipes;
    }

    public int getRecipeCount() {
        return recipeCount;
    }

    public void setRecipeCount(int recipeCount) {
        this.recipeCount = recipeCount;
    }

    public int getUnusedInventoryCount() {
        return unusedInventoryCount;
    }

    public void setUnusedInventoryCount(int unusedInventoryCount) {
        this.unusedInventoryCount = unusedInventoryCount;
    }
}
