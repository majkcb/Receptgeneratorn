package models.categories;

import models.Ingredient;
import models.Recipe;

import java.util.List;

public class LunchRecipe extends Recipe {
    private int servings;

    public LunchRecipe(String name, String description, List<Ingredient> ingredient, int servings) {
        super(name, description, ingredient);
        this.servings = servings;
    }

    public int getServings() {
        return this.servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    @Override
    public String toString() {
        return super.toString() + "\nServings: " + this.servings;
    }
}
