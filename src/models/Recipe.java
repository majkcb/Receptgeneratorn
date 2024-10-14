package models;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Instruction {

    private final String name;
    private final String description;
    private final List<Ingredient> ingredient;
    private List<String> instructions;

    public Recipe(String name, String description, List<Ingredient> ingredient) {
        this.name = name;
        this.description = description;
        this.ingredient = ingredient;
        this.instructions = new ArrayList<>();
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
    public void addStep(String step) {
        instructions.add(step);
    }

    @Override
    public List<String> getSteps() {
        return instructions;
    }


    @Override
    public String toString() {
        return "Recipe: " + name + "\nDescription: " + description + "\nIngredients: " + ingredient;
    }

}
