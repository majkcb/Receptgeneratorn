import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeTest {
    RecipeHandler<Recipe> recipeHandler;
    ServingTemperature servingTemperature;

    @BeforeEach
    void setUp() {
        recipeHandler = new RecipeHandler<>();
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
    public void testBreakfast() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Ägg", 5, "st"));

        BreakfastRecipe breakfastRecipe = new BreakfastRecipe("Äggröra", "Såhär gör du", ingredients, ServingTemperature.VARM);
        recipeHandler.addRecipe(breakfastRecipe);

        assertEquals(ServingTemperature.VARM, ((BreakfastRecipe) recipeHandler.getAllRecipes().getFirst()).getServingTemperature());

        System.out.println(recipeHandler.getAllRecipes());

    }

    @Test
    public void testLunch() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Nötfärs", 5, "kg"));

        LunchRecipe lunchRecipe = new LunchRecipe("Köttbullar", "Såhär gör du", ingredients, 2);
        recipeHandler.addRecipe(lunchRecipe);

        assertEquals(2, ((LunchRecipe) recipeHandler.getAllRecipes().getFirst()).getServings());

        System.out.println(recipeHandler.getAllRecipes());

    }

    @Test
    public void testDinner() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Kyckling", 200, "gram"));
        ingredients.add(new Ingredient("ananas", 1, "kg"));

        DinnerRecipe dinnerRecipe = new DinnerRecipe("Flygande jacob", "Såhär gör du", ingredients, 50);
        recipeHandler.addRecipe(dinnerRecipe);

        assertEquals(50, ((DinnerRecipe) recipeHandler.getAllRecipes().getFirst()).getCookingTime());

        System.out.println(recipeHandler.getAllRecipes());
    }

    @Test
    void removeRecipe() {
        recipeHandler.removeRecipe("Pannkakor");
        assertEquals(0, recipeHandler.getAllRecipes().size());
        System.out.println(recipeHandler.getAllRecipes());
    }
}
