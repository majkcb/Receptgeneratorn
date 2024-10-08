import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeTest {
    private RecipeHandler recipeHandler;

    @BeforeEach
    void setUp() {
        recipeHandler = new RecipeHandler();
    }

    @Test
    void testAddRecipe() {

        List<Ingredient> ingredients = new ArrayList<>();

        ingredients.add(new Ingredient("Ägg", 4, "st"));
        ingredients.add(new Ingredient("Mjölk", 5, "dl"));
        ingredients.add(new Ingredient("Mjöl", 6, "dl"));

        Recipe pannkakor = new Recipe("Pannkakor", "Såhär gör du pannkakor", ingredients);
        recipeHandler.addRecipe(pannkakor);

        assertEquals(1, recipeHandler.getAllRecipes().size());
        System.out.println(recipeHandler.getAllRecipes());
    }

    @Test
    void removeRecipe() {
        recipeHandler.removeRecipe("Pannkakor");
        assertEquals(0, recipeHandler.getAllRecipes().size());
        System.out.println(recipeHandler.getAllRecipes());
    }
}
