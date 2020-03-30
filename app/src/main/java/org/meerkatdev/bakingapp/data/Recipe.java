package org.meerkatdev.bakingapp.data;

import java.util.List;

public class Recipe {

    public int id;
    public String name;
    public Ingredient[] ingredients;
    public RecipeStep[] steps;
    public int servings;
    public String image;

    public Recipe(int id, String name, Ingredient[] ingredients, RecipeStep[] steps, int servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }
}
