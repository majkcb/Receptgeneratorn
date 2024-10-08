import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeTest {
    @Test
    void testAddRecipe() {
        Recipe recipe = new Recipe("Pannkakor");
        assertEquals("Recept av pannkakor", recipe.add());
    }
}
