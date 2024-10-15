import models.Ingredient;
import models.Recipe;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class InstructionTest {

    @Test
    void testRemoveStep() {
        Recipe recipe = new Recipe("A cucumber recipe", "A wonderful dish", new ArrayList<>());

        recipe.addStep("Step 1: Add cucumber");
        recipe.addStep("Step 2: Mix cucumber");

        recipe.removeStep("Step 1: Add cucumber");

        List<String> steps = recipe.getSteps();
        assertEquals(1, steps.size());
        assertEquals("Step 2: Mix cucumber", steps.getFirst());
    }

    @Test
    void testSetSteps() {
        Recipe recipe = new Recipe("A cucumber recipe", "A wonderful dish", new ArrayList<>());

        List<String> newSteps = List.of("Step 1: Chop cucumber", "Step 2: Mix cucumber");
        recipe.setSteps(newSteps);

        List<String> steps = recipe.getSteps();
        assertEquals(2, steps.size());
        assertEquals("Step 1: Chop cucumber", steps.get(0));
        assertEquals("Step 2: Mix cucumber", steps.get(1));
    }

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
