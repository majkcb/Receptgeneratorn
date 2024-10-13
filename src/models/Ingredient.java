package models;

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

    public int getQuantity() {
        return this.quantity;
    }

    public String getUnit() {
        return this.unit;
    }

    @Override
    public String toString() {
        return "\nIngredient: " + name + "\nKvantitet: " +  quantity + "\nEnhet: " + unit;
    }

}
