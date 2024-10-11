package models;

import java.util.List;

public class Recipe {
    private final String name;
    private final String description;
    private final List<Ingredient> ingredient;

    public Recipe(String name, String description, List<Ingredient> ingredient) {
        this.name = name;
        this.description = description;
        this.ingredient = ingredient;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Ingredient> getIngredients() {
        return ingredient;
    }

    @Override
    public String toString() {
        return "Recept: " + name + "\nBeskrivning: " + description + "\nIngredienser: " + ingredient;
    }

}
