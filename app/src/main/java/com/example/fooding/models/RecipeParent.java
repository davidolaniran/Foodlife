package com.example.fooding.models;

public class RecipeParent {
    private String recipeCategory;

    public RecipeParent(String recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public String recipeCategory() {
        return recipeCategory;
    }
}