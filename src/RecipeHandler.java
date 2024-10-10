import java.util.ArrayList;
import java.util.List;

public class RecipeHandler<T extends Recipe> {
    private final List<T> recipes;

    public RecipeHandler() {
        this.recipes = new ArrayList<>();
    }

    public void addRecipe(T recipe) {
        this.recipes.add(recipe);
    }

    public void removeRecipe(String name) {
        recipes.removeIf(recipe -> recipe.getName().equals(name));
    }

    public List<T> getAllRecipes() {
        return new ArrayList<>(recipes);
    }

    @Override
    public String toString() {
        return recipes.toString();
    }
}
