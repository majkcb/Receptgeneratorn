import java.util.List;

public class BreakfastRecipe extends Recipe {
    private ServingTemperature servingTemperature;

    public BreakfastRecipe(String name, String description, List<Ingredient> ingredient, ServingTemperature servingTemperature) {
        super(name, description, ingredient);
        this.servingTemperature = servingTemperature;
    }

    public ServingTemperature getServingTemperature() {
        return servingTemperature;
    }

    public void setServingTemperature(ServingTemperature servingTemperature) {
        this.servingTemperature = servingTemperature;
    }

    @Override
    public String toString() {
        return super.toString() + "\nServeringstemperatur: " + servingTemperature;
    }
}
