import models.*;
import models.subclasses.BreakfastRecipe;
import models.subclasses.DinnerRecipe;
import models.subclasses.LunchRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // TODO Refactor methods and packages

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

        int subChoice = inputRecipeType(scanner);

        if (subChoice < 1 || subChoice > 3) {
            System.out.println("Invalid input. Returning to the main menu.");
            return;
        }

        String recipeName = inputRecipeName(scanner);
        String recipeDescription = inputRecipeDescription(scanner);
        List<String> steps = addInstructions(scanner);
        List<Ingredient> ingredients = new ArrayList<>();
        addIngredients(scanner, ingredients);

        switch (subChoice) {
            case 1 -> {
                ServingTemperature servingTemperature = inputServingTemperature(scanner);
                BreakfastRecipe breakfastRecipe = new BreakfastRecipe(recipeName, recipeDescription, ingredients, servingTemperature);
                breakfastRecipe.setSteps(steps);
                recipeHandler.addRecipe(breakfastRecipe);
                System.out.println("Breakfast recipe \"" + recipeName + "\" has been added.");
            }
            case 2 -> {
                int servings = inputServings(scanner);
                LunchRecipe lunchRecipe = new LunchRecipe(recipeName, recipeDescription, ingredients, servings);
                lunchRecipe.setSteps(steps);
                recipeHandler.addRecipe(lunchRecipe);
                System.out.println("Lunch recipe \"" + recipeName + "\" has been added");
            }
            case 3 -> {
                int cookingTime = inputCookingTime(scanner);
                DinnerRecipe dinnerRecipe = new DinnerRecipe(recipeName, recipeDescription, ingredients, cookingTime);
                dinnerRecipe.setSteps(steps);
                recipeHandler.addRecipe(dinnerRecipe);
                System.out.println("Dinner recipe \"" + recipeName + "\" has been added.");
            }
            default -> System.out.println("Invalid choice");
        }
    }

    private static int inputRecipeType(Scanner scanner) {
        System.out.println("\nWhat kind of recipe do you wish to create?");
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
            return -1;
        }
        scanner.nextLine();

        return subChoice;
    }

    private static String inputRecipeName(Scanner scanner) {
        System.out.print("Recipe name: ");
        return scanner.nextLine();
    }

    private static String inputRecipeDescription(Scanner scanner) {
        System.out.print("Recipe description: ");
        return scanner.nextLine();
    }

    private static ServingTemperature inputServingTemperature(Scanner scanner) {
        System.out.println("\nChoose serving temperature:");
        System.out.println("1. Cold");
        System.out.println("2. Warm");
        System.out.println("3. Hot");

        int temperatureChoice = scanner.nextInt();
        scanner.nextLine();

        return switch (temperatureChoice) {
            case 1 -> ServingTemperature.COLD;
            case 2 -> ServingTemperature.WARM;
            case 3 -> ServingTemperature.HOT;
            default -> {
                System.out.println("Invalid choice. Default value 'WARM' will be used.");
                yield ServingTemperature.WARM;
            }
        };
    }

    private static int inputServings(Scanner scanner) {
        System.out.print("\nEnter the number of servings: ");
        int servings;
        try {
            servings = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Number of servings set to 1.");
            servings = 1;
            scanner.nextLine();
        }
        scanner.nextLine();
        return servings;
    }

    private static int inputCookingTime(Scanner scanner) {
        System.out.print("\nEnter cooking time in minutes: ");
        int cookingTime;
        try {
            cookingTime = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Cooking time set to 30 minutes.");
            cookingTime = 30;
            scanner.nextLine();
        }
        scanner.nextLine();
        return cookingTime;
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
            for (Recipe recipe : allRecipes) {
                System.out.println(recipe);
                List<String> steps = recipe.getSteps();
                if (steps != null && !steps.isEmpty()) {
                    System.out.println("Instructions:");
                    for (int i = 0; i < steps.size(); i++) {
                        System.out.println((i + 1) + ". " + steps.get(i));
                    }
                }
                System.out.println("---------------------------");
            }
        }
    }

    private static List<String> addInstructions(Scanner scanner) {
        List<String> steps = new ArrayList<>();
        String step;
        String addMore;

        while (true) {
            System.out.print("Enter instruction step: ");
            step = scanner.nextLine().trim();
            if (!step.isEmpty()) {
                steps.add(step);
            } else {
                System.out.println("Step cannot be empty.");
                continue;
            }

            while (true) {
                System.out.print("Would you like to add another step? (Yes/No): ");
                addMore = scanner.nextLine().trim();

                if (addMore.equalsIgnoreCase("Yes")) {
                    break;
                } else if (addMore.equalsIgnoreCase("No")) {
                    return steps;
                } else {
                    System.out.println("Invalid input, please type 'Yes' or 'No'.");
                }
            }
        }
    }

}
