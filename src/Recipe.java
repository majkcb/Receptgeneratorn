import java.util.List;

public class Recipe {
    private final String name;
    private final String description;
    private final String ingredient;

    public Recipe(String name, String description, String ingredient) {
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

    public String getIngredients() {
        return ingredient;
    }

    @Override
    public String toString() {
        return "Recept: " + name + "\nBeskrivning: " + description + "\nIngredient: " + ingredient;
    }

}
