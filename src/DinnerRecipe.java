import java.util.List;

public class DinnerRecipe extends Recipe {
    private int cookingTime;

    public DinnerRecipe(String name, String description, List<Ingredient> ingredient, int cookingTime) {
        super(name, description, ingredient);
        this.cookingTime = cookingTime;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCooking Time: " + cookingTime + " min";
    }
}
