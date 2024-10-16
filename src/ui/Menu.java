package ui;

import models.*;
import services.RecipeHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private final RecipeHandler<Recipe> recipeHandler;

    public Menu(Scanner scanner, RecipeHandler<Recipe> recipeHandler) {
        this.scanner = scanner;
        this.recipeHandler = recipeHandler;
    }

    public void displayMenu() {
        String[] menuChoices = {
                "1. Add recipe",
                "2. Remove recipe",
                "3. Print all recipes",
                "0. Exit program"
        };
        menuHandler(menuChoices);
    }

    private void menuHandler(String[] menuChoices) {
        while (true) {
            System.out.println("\nWelcome to the recipe generator!");
            for (String menuChoice : menuChoices) {
                System.out.println(menuChoice);
            }

            int choice;
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number from the menu.");
                continue;
            }

            switch (choice) {
                case 1:
                    addNewRecipe();
                    break;
                case 2:
                    removeRecipe();
                    break;
                case 3:
                    printAllRecipes();
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again");
            }
        }
    }

    private void addNewRecipe() {
        int subChoice = inputRecipeType();

        if (subChoice < 1 || subChoice > 3) {
            System.out.println("Invalid input. Returning to the main menu.");
            return;
        }

        String recipeName = inputRecipeName();
        String recipeDescription = inputRecipeDescription();
        List<String> steps = addInstructions();
        List<Ingredient> ingredients = new ArrayList<>();
        addIngredients(ingredients);

        switch (subChoice) {
            case 1 -> {
                ServingTemperature servingTemperature = inputServingTemperature();
                BreakfastRecipe breakfastRecipe = new BreakfastRecipe(recipeName, recipeDescription, ingredients, servingTemperature);
                breakfastRecipe.setSteps(steps);
                recipeHandler.addRecipe(breakfastRecipe);
                System.out.println("Breakfast recipe \"" + recipeName + "\" has been added.");
            }
            case 2 -> {
                int servings = inputServings();
                LunchRecipe lunchRecipe = new LunchRecipe(recipeName, recipeDescription, ingredients, servings);
                lunchRecipe.setSteps(steps);
                recipeHandler.addRecipe(lunchRecipe);
                System.out.println("Lunch recipe \"" + recipeName + "\" has been added");
            }
            case 3 -> {
                int cookingTime = inputCookingTime();
                DinnerRecipe dinnerRecipe = new DinnerRecipe(recipeName, recipeDescription, ingredients, cookingTime);
                dinnerRecipe.setSteps(steps);
                recipeHandler.addRecipe(dinnerRecipe);
                System.out.println("Dinner recipe \"" + recipeName + "\" has been added.");
            }
            default -> System.out.println("Invalid choice");
        }
    }

    private int inputRecipeType() {
        System.out.println("\nWhat kind of recipe do you wish to create?");
        System.out.println("1. Breakfast");
        System.out.println("2. Lunch");
        System.out.println("3. Dinner");
        System.out.print("\nEnter your choice: ");

        int subChoice;
        try {
            subChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Returning to the main menu.");
            return -1;
        }

        return subChoice;
    }

    private String inputRecipeName() {
        System.out.print("Recipe name: ");
        return scanner.nextLine();
    }

    private String inputRecipeDescription() {
        System.out.print("Recipe description: ");
        return scanner.nextLine();
    }

    private ServingTemperature inputServingTemperature() {
        System.out.println("\nChoose serving temperature:");
        System.out.println("1. Cold");
        System.out.println("2. Warm");
        System.out.println("3. Hot");
        System.out.print("Enter your choice: ");

        int temperatureChoice;
        try {
            temperatureChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice. Default value 'WARM' will be used.");
            return ServingTemperature.WARM;
        }

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

    private int inputServings() {
        System.out.print("\nEnter the number of servings: ");
        int servings;
        try {
            servings = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Number of servings set to 1.");
            servings = 1;
        }
        return servings;
    }

    private int inputCookingTime() {
        System.out.print("\nEnter cooking time in minutes: ");
        int cookingTime;
        try {
            cookingTime = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Cooking time set to 30 minutes.");
            cookingTime = 30;
        }
        return cookingTime;
    }

    private void addIngredients(List<Ingredient> ingredients) {
        while (true) {
            System.out.print("Ingredient name: ");
            String ingredientName = scanner.nextLine().trim();

            int quantity;
            while (true) {
                System.out.print("Quantity: ");
                String quantityInput = scanner.nextLine().trim();
                try {
                    quantity = Integer.parseInt(quantityInput);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter an integer for the quantity.");
                }
            }

            System.out.print("Unit (e.g., g, pcs, kg, dl): ");
            String unit = scanner.nextLine().trim();

            ingredients.add(new Ingredient(ingredientName, quantity, unit));

            while (true) {
                System.out.print("Would you like to add another ingredient? (Yes/No): ");
                String addMore = scanner.nextLine().trim();

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

    private void removeRecipe() {
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

    private void printAllRecipes() {
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

    private List<String> addInstructions() {
        List<String> steps = new ArrayList<>();
        while (true) {
            System.out.print("Enter instruction step: ");
            String step = scanner.nextLine().trim();
            if (!step.isEmpty()) {
                steps.add(step);
            } else {
                System.out.println("Step cannot be empty.");
                continue;
            }

            while (true) {
                System.out.print("Would you like to add another step? (Yes/No): ");
                String addMore = scanner.nextLine().trim();

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

