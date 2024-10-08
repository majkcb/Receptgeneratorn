import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Ingredient ingredient1 = new Ingredient("Mjöl", 3, "kg");
        Ingredient ingredient2 = new Ingredient("Mjölk", 50, "L");
        Ingredient ingredient3 = new Ingredient("Ägg", 6, "st");

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);

        RecipeHandler recipeHandler = new RecipeHandler();
        Recipe pannkaka = new Recipe("Pannkaka", "Såhär gör du", ingredients);
        recipeHandler.addRecipe(pannkaka);

        System.out.println(recipeHandler.getAllRecipes());



    }
}