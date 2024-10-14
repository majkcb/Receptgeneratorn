import models.*;
import models.subclasses.BreakfastRecipe;
import models.subclasses.DinnerRecipe;
import models.subclasses.LunchRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // TODO Add Interface "Instructions"

        Scanner scanner = new Scanner(System.in);
        RecipeHandler<Recipe> recipeHandler = new RecipeHandler<>();

        String[] menuChoices = {
                "1. Add recipe",
                "2. Remove recipe",
                "3. Print all recipes",
                "0. Exit program"
        };
        menuHandler(menuChoices, scanner, recipeHandler);
    }

    private static void menuHandler(String[] menuChoices, Scanner scanner, RecipeHandler<Recipe> recipeHandler) {

        while (true) {
            System.out.println("\nWelcome to the recipe generator!");
            for (String menuChoice : menuChoices) {
                System.out.println(menuChoice);
            }

            int menuChoice;
            try {
                menuChoice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number from the menu.");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();

            switch (menuChoice) {
                case 1:
                    addNewRecipe(scanner, recipeHandler);
                    break;
                case 2:
                    removeRecipe(scanner, recipeHandler);
                    break;
                case 3:
                    printAllRecipes(recipeHandler);
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again");
            }
        }
    }

    private static void addNewRecipe(Scanner scanner, RecipeHandler<Recipe> recipeHandler) {

        System.out.println("\nVWhat kind of recipe do you wish to create?");
        System.out.println("1. Breakfast");
        System.out.println("2. Lunch");
        System.out.println("3. Dinner");
        System.out.print("Enter your choice: ");

        int subChoice;
        try {
            subChoice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Returning to the main menu.");
            scanner.nextLine();
            return;
        }
        scanner.nextLine();

        if (subChoice < 1 || subChoice > 3) {
            System.out.println("Invalid input. Returning to the main menu.");
            return;
        }

        System.out.print("Recipe name: ");
        String recipeName = scanner.nextLine();

        System.out.print("Recipe description: ");
        String recipeDescription = scanner.nextLine();

        List<Ingredient> ingredients = new ArrayList<>();
        addIngredients(scanner, ingredients);

        addRecipe(subChoice, scanner, recipeName, recipeDescription, ingredients, recipeHandler);
    }

    private static void removeRecipe(Scanner scanner, RecipeHandler<Recipe> recipeHandler) {

        System.out.print("\nEnter the name of the recipe to be removed: ");
        String recipeToRemove = scanner.nextLine();

        List<Recipe> allRecipes = recipeHandler.getAllRecipes();
        boolean recipeExists = allRecipes.stream().anyMatch(r -> r.getName().equalsIgnoreCase(recipeToRemove));

        if (recipeExists) {
            recipeHandler.removeRecipe(recipeToRemove);
            System.out.println("The recipe \"" + recipeToRemove + "\" has been removed.");
        } else {
            System.out.println("The recipe \"" + recipeToRemove + "\" was not found");
        }
    }

    private static void printAllRecipes(RecipeHandler<Recipe> recipeHandler) {

        System.out.println("\nYour recipes:");
        List<Recipe> allRecipes = recipeHandler.getAllRecipes();
        if (allRecipes.isEmpty()) {
            System.out.println("No recipes to display.");
        } else {
            allRecipes.forEach(System.out::println);
        }
    }

    private static void addRecipe(int choice, Scanner scanner, String recipeName, String recipeDescription, List<Ingredient> ingredients, RecipeHandler<Recipe> recipeHandler) {

        switch (choice) {
            case 1 -> {
                System.out.println("Choose serving temperature:");
                System.out.println("1. Cold");
                System.out.println("2. Warm");
                System.out.println("3. Hot");

                int temperatureChoice = scanner.nextInt();
                scanner.nextLine();

                ServingTemperature servingTemperature;

                switch (temperatureChoice) {
                    case 1 -> servingTemperature = ServingTemperature.COLD;
                    case 2 -> servingTemperature = ServingTemperature.WARM;
                    case 3 -> servingTemperature = ServingTemperature.HOT;
                    default -> {
                        System.out.println("Invalid choice. Default value 'WARM' will be used.");
                        servingTemperature = ServingTemperature.WARM;
                    }
                }

                BreakfastRecipe breakfastRecipe = new BreakfastRecipe(recipeName, recipeDescription, ingredients, servingTemperature);
                recipeHandler.addRecipe(breakfastRecipe);
                System.out.println("Breakfast recipe \"" + recipeName + "\" has been added.");
            }
            case 2 -> {
                System.out.print("Enter the number of servings: ");
                int servings;
                try {
                    servings = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input. Number of servings set to 1.");
                    servings = 1;
                    scanner.nextLine();
                }
                scanner.nextLine();

                LunchRecipe lunchRecipe = new LunchRecipe(recipeName, recipeDescription, ingredients, servings);
                recipeHandler.addRecipe(lunchRecipe);
                System.out.println("Lunch recipe \"" + recipeName + "\" has been added");
            }
            case 3 -> {
                System.out.print("Enter cooking time in minutes: ");
                int cookingTime;
                try {
                    cookingTime = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input. Cooking time set to 30 minutes.");
                    cookingTime = 30;
                    scanner.nextLine();
                }
                scanner.nextLine();

                DinnerRecipe dinnerRecipe = new DinnerRecipe(recipeName, recipeDescription, ingredients, cookingTime);
                recipeHandler.addRecipe(dinnerRecipe);
                System.out.println("Dinner recipe \"" + recipeName + "\" has been added.");
            }
            default -> System.out.println("Invalid choice");
        }
    }

    private static void addIngredients(Scanner scanner, List<Ingredient> ingredients) {

        String ingredientName;
        int quantity;
        String unit;
        String addMore;

        while (true) {
            System.out.print("Ingredient name: ");
            ingredientName = scanner.nextLine().trim();

            System.out.print("Quantity: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer for the quantity.");
                scanner.next();
                System.out.print("Quantity: ");
            }
            quantity = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Unit (e.g., g, pcs, kg, dl): ");
            unit = scanner.nextLine().trim();

            ingredients.add(new Ingredient(ingredientName, quantity, unit));

            while (true) {
                System.out.print("Would you like to add another ingredient? (Yes/No): ");
                addMore = scanner.nextLine().trim();

                if (addMore.equalsIgnoreCase("Yes")) {
                    break;
                } else if (addMore.equalsIgnoreCase("No")) {
                    return;
                } else {
                    System.out.println("Invalid input, please type 'Yes' or 'No'.");
                }
            }
        }

    }
}
