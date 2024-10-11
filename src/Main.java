import models.*;
import models.subclasses.BreakfastRecipe;
import models.subclasses.DinnerRecipe;
import models.subclasses.LunchRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RecipeHandler<Recipe> recipeHandler = new RecipeHandler<>();


//        System.out.println("Välkommen till Receptgeneratorn!");
//        System.out.println("1. Lägg till Recept");
//        System.out.println("2. Visa alla Recept");
//        System.out.println("3. Ändra Recept");

        System.out.println("Vilken typ av recept vill du skapa?");
        System.out.println("1. Frukost");
        System.out.println("2. Lunch");
        System.out.println("3. Middag");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Receptets namn: ");
        String recipeName = scanner.nextLine();
        System.out.println("Recept beskrivning: ");
        String recipeDescription = scanner.nextLine();

        List<Ingredient> ingredients = new ArrayList<>();

        System.out.println("Lägg till ingredienser: ");

        addIngredients(scanner, ingredients);

        addRecipe(choice, scanner, recipeName, recipeDescription, ingredients, recipeHandler);

        System.out.println("Dina recept: " + recipeHandler.getAllRecipes());

    }

    private static void addRecipe(int choice, Scanner scanner, String recipeName, String recipeDescription, List<Ingredient> ingredients, RecipeHandler<Recipe> recipeHandler) {
        switch (choice) {
                case 1 -> {
                    System.out.print("Ange serveringstemperatur (Kall/Varm: ");
                    String temperature = scanner.nextLine();
                    ServingTemperature servingTemperature = ServingTemperature.valueOf(temperature.toUpperCase());
                    BreakfastRecipe breakfastRecipe = new BreakfastRecipe(recipeName, recipeDescription, ingredients, servingTemperature);
                    recipeHandler.addRecipe(breakfastRecipe);
                }
                case 2 -> {
                    System.out.print("Ange antal portioner: ");
                    int servings = scanner.nextInt();
                    LunchRecipe lunchRecipe = new LunchRecipe(recipeName, recipeDescription, ingredients, servings);
                    recipeHandler.addRecipe(lunchRecipe);
                }
                case 3 -> {
                    System.out.print("Ange tillagningstid i minuter: ");
                    int cookingTime = scanner.nextInt();
                    DinnerRecipe dinnerRecipe = new DinnerRecipe(recipeName, recipeDescription, ingredients, cookingTime);
                    recipeHandler.addRecipe(dinnerRecipe);
                }
                default -> System.out.println("Ogiltigt val.");
            }
    }

    private static void addIngredients(Scanner scanner, List<Ingredient> ingredients) {
        String ingredientName;
        int quantity;
        String unit;
        String addMore;

        do {
            System.out.println("Ingrediens: ");
            ingredientName = scanner.nextLine();

            System.out.println("Mängd: ");
            quantity = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enhet: ");
            unit = scanner.nextLine();

            ingredients.add(new Ingredient(ingredientName, quantity, unit));

            System.out.println("Vill du lägga till en till ingrediens? (Ja/Nej)");
            addMore = scanner.nextLine();

        } while (addMore.equalsIgnoreCase("Ja"));
    }
}