import models.*;
import services.RecipeHandler;
import ui.Menu;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        RecipeHandler<Recipe> recipeHandler = new RecipeHandler<>();

        Menu menu = new Menu(scanner, recipeHandler);
        menu.displayMenu();

        scanner.close();
    }
}