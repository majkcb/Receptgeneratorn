import models.*;
import models.subclasses.BreakfastRecipe;
import models.subclasses.DinnerRecipe;
import models.subclasses.LunchRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // TODO Ändra all text till engelska
        // TODO Ta bort beskrivning
        // TODO Lägg till Interface "Instructions"

        Scanner scanner = new Scanner(System.in);
        RecipeHandler<Recipe> recipeHandler = new RecipeHandler<>();

        String[] menuChoices = {
                "1. Lägg till recept",
                "2. Ta bort recept",
                "3. Visa alla recept",
                "0. Avsluta programmet"
        };
        menuHandler(menuChoices, scanner, recipeHandler);
    }

    private static void menuHandler(String[] menuChoices, Scanner scanner, RecipeHandler<Recipe> recipeHandler) {

        while (true) {
            System.out.println("\nVälkommen till Receptgeneratorn! Vad vill du göra?");
            for (String menuChoice : menuChoices) {
                System.out.println(menuChoice);
            }

            int menuChoice;
            try {
                menuChoice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Felaktig inmatning. Ange ett nummer från menyn.");
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
                    System.out.println("Avslutar programmet...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Ogiltigt val, försök igen...");
            }
        }
    }

    private static void addNewRecipe(Scanner scanner, RecipeHandler<Recipe> recipeHandler) {

        System.out.println("\nVilken typ av recept vill du skapa?");
        System.out.println("1. Frukost");
        System.out.println("2. Lunch");
        System.out.println("3. Middag");
        System.out.print("Ange ditt val: ");

        int subChoice;
        try {
            subChoice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Felaktig inmatning. Återgår till huvudmenyn.");
            scanner.nextLine();
            return;
        }
        scanner.nextLine();

        if (subChoice < 1 || subChoice > 3) {
            System.out.println("Ogiltigt val. Återgår till huvudmenyn.");
            return;
        }

        System.out.print("Receptets namn: ");
        String recipeName = scanner.nextLine();

        System.out.print("Recept beskrivning: ");
        String recipeDescription = scanner.nextLine();

        List<Ingredient> ingredients = new ArrayList<>();
        addIngredients(scanner, ingredients);

        addRecipe(subChoice, scanner, recipeName, recipeDescription, ingredients, recipeHandler);
    }

    private static void removeRecipe(Scanner scanner, RecipeHandler<Recipe> recipeHandler) {

        System.out.print("\nAnge namnet på receptet som ska tas bort: ");
        String recipeToRemove = scanner.nextLine();

        List<Recipe> allRecipes = recipeHandler.getAllRecipes();
        boolean recipeExists = allRecipes.stream().anyMatch(r -> r.getName().equalsIgnoreCase(recipeToRemove));

        if (recipeExists) {
            recipeHandler.removeRecipe(recipeToRemove);
            System.out.println("Receptet \"" + recipeToRemove + "\" har tagits bort.");
        } else {
            System.out.println("Receptet \"" + recipeToRemove + "\" hittades inte.");
        }
    }

    private static void printAllRecipes(RecipeHandler<Recipe> recipeHandler) {

        System.out.println("\nDina recept:");
        List<Recipe> allRecipes = recipeHandler.getAllRecipes();
        if (allRecipes.isEmpty()) {
            System.out.println("Inga recept att visa.");
        } else {
            allRecipes.forEach(System.out::println);
        }
    }

    private static void addRecipe(int choice, Scanner scanner, String recipeName, String recipeDescription, List<Ingredient> ingredients, RecipeHandler<Recipe> recipeHandler) {

        switch (choice) {
            case 1 -> {
                System.out.println("Välj serveringstemperatur:");
                System.out.println("1. Kall");
                System.out.println("2. Varm");
                System.out.println("3. Het");

                int temperatureChoice = scanner.nextInt();
                scanner.nextLine();

                ServingTemperature servingTemperature;

                switch (temperatureChoice) {
                    case 1 -> servingTemperature = ServingTemperature.COLD;
                    case 2 -> servingTemperature = ServingTemperature.WARM;
                    case 3 -> servingTemperature = ServingTemperature.HOT;
                    default -> {
                        System.out.println("Ogiltigt val. Standardvärde 'VARM' används.");
                        servingTemperature = ServingTemperature.WARM;
                    }
                }

                BreakfastRecipe breakfastRecipe = new BreakfastRecipe(recipeName, recipeDescription, ingredients, servingTemperature);
                recipeHandler.addRecipe(breakfastRecipe);
                System.out.println("Frukostrecept \"" + recipeName + "\" har lagts till.");
            }
            case 2 -> {
                System.out.print("Ange antal portioner: ");
                int servings;
                try {
                    servings = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Felaktig inmatning. Antal portioner satt till 1.");
                    servings = 1;
                    scanner.nextLine();
                }
                scanner.nextLine();

                LunchRecipe lunchRecipe = new LunchRecipe(recipeName, recipeDescription, ingredients, servings);
                recipeHandler.addRecipe(lunchRecipe);
                System.out.println("Lunchrecept \"" + recipeName + "\" har lagts till.");
            }
            case 3 -> {
                System.out.print("Ange tillagningstid i minuter: ");
                int cookingTime;
                try {
                    cookingTime = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Felaktig inmatning. Tillagningstid satt till 30 minuter.");
                    cookingTime = 30;
                    scanner.nextLine();
                }
                scanner.nextLine();

                DinnerRecipe dinnerRecipe = new DinnerRecipe(recipeName, recipeDescription, ingredients, cookingTime);
                recipeHandler.addRecipe(dinnerRecipe);
                System.out.println("Middagsrecept \"" + recipeName + "\" har lagts till.");
            }
            default -> System.out.println("Ogiltigt val.");
        }
    }

    private static void addIngredients(Scanner scanner, List<Ingredient> ingredients) {

        String ingredientName;
        int quantity;
        String unit;
        String addMore;

        while (true) {
            System.out.print("Ingrediens namn: ");
            ingredientName = scanner.nextLine().trim();

            System.out.print("Mängd: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Felaktig inmatning. Ange ett heltal för mängden.");
                scanner.next();
                System.out.print("Mängd: ");
            }
            quantity = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enhet (t ex: g, st, kg, dl): ");
            unit = scanner.nextLine().trim();

            ingredients.add(new Ingredient(ingredientName, quantity, unit));

            while (true) {
                System.out.print("Vill du lägga till en till ingrediens? (Ja/Nej): ");
                addMore = scanner.nextLine().trim();

                if (addMore.equalsIgnoreCase("Ja")) {
                    break;
                } else if (addMore.equalsIgnoreCase("Nej")) {
                    return;
                } else {
                    System.out.println("Felaktig inmatning, skriv 'Ja' eller 'Nej'.");
                }
            }
        }

    }
}
