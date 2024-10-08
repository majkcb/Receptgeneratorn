import java.util.ArrayList;
import java.util.List;

public class RecipeHandler {
    private final List<Recipe> recipes;

    public RecipeHandler() {
        this.recipes = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }

    public void removeRecipe(String name) {
        recipes.removeIf(recipe -> recipe.getName().equals(name));
    }

    public List<Recipe> getAllRecipes() {
        return new ArrayList<>(recipes);
    }
}
