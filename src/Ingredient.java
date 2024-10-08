public class Ingredient {
    private final String name;
    private final int quantity;
    private final String unit;

    public Ingredient(String name, int quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName() {
        return this.name;
    }

    public String getUnit() {
        return this.unit;
    }

    @Override
    public String toString() {
        return "Ingredient: " + name + "\nKvantitet: " +  quantity + "Enhet: " + unit;
    }


}
