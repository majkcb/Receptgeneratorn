import models.Ingredient;
import models.Recipe;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class InstructionTest {

    // TODO Add Remove Step
    // TODO Add Set Steps

    @Test
    void testAddAndGetInstructions() {

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Cucumber1", 40, "st"));
        ingredients.add(new Ingredient("Cucumber2", 50, "dl"));
        ingredients.add(new Ingredient("Cucumber3", 7, "dl"));

        Recipe recipe = new Recipe("A cucumber recipe", "A wonderful dish", ingredients);

        recipe.addStep("Step 1: Add cucumber");
        recipe.addStep("Step 2: Mix cucumber");

        List<String> steps = recipe.getSteps();
        assertEquals(2, steps.size());
        assertEquals("Step 1: Add cucumber", steps.get(0));
        assertEquals("Step 2: Mix cucumber", steps.get(1));
    }
}
