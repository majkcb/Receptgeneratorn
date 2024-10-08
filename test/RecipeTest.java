import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeTest {
    private RecipeHandler recipeHandler;

    @BeforeEach
    void setUp() {
        recipeHandler = new RecipeHandler();
    }

    @Test
    void testAddRecipe() {
        Recipe pannkakor = new Recipe("Pannkakor", "Såhär gör du pannkakor", "Mjöl, Mjölk, Ägg");
        recipeHandler.addRecipe(pannkakor);

        assertEquals(1, recipeHandler.getAllRecipes().size());
    }
}
