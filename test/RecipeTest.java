import models.*;
import models.subclasses.BreakfastRecipe;
import models.subclasses.DinnerRecipe;
import models.subclasses.LunchRecipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeTest {
    RecipeHandler<Recipe> recipeHandler;

    @BeforeEach
    void setUp() {
        recipeHandler = new RecipeHandler<>();
    }

    @Test
    void testAddIngredient() {
        Ingredient ingredient = new Ingredient("Ägg", 100, "st");
        assertEquals("Ägg", ingredient.getName());
        assertEquals(100, ingredient.getQuantity());
        assertEquals("st", ingredient.getUnit());
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
    }

    @Test
    public void testBreakfast() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Ägg", 5, "st"));

        BreakfastRecipe breakfastRecipe = new BreakfastRecipe("Äggröra", "Såhär gör du", ingredients, ServingTemperature.WARM);
        recipeHandler.addRecipe(breakfastRecipe);

        assertEquals(ServingTemperature.WARM, ((BreakfastRecipe) recipeHandler.getAllRecipes().getFirst()).getServingTemperature());
    }

    @Test
    public void testLunch() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Nötfärs", 5, "kg"));

        LunchRecipe lunchRecipe = new LunchRecipe("Köttbullar", "Såhär gör du", ingredients, 2);
        recipeHandler.addRecipe(lunchRecipe);

        assertEquals(2, ((LunchRecipe) recipeHandler.getAllRecipes().getFirst()).getServings());
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
