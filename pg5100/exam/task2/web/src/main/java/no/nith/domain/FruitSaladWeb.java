package no.nith.domain;

import no.nith.entities.FruitSalad;

import java.util.Map;

/**
 * @author Simen Bekkhus
 */
public class FruitSaladWeb {
    private int id;
    private String name;
    private double price;
    private String instructions;
    private Map<FruitWeb, Integer> ingredients;

    public FruitSaladWeb(final FruitSalad fruitSalad) {
        this.id = fruitSalad.getSaladId();
        this.name = fruitSalad.getName();
        this.price = fruitSalad.getPrice();
        this.instructions = fruitSalad.getInstructions();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(final String instructions) {
        this.instructions = instructions;
    }

    public Map<FruitWeb, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(final Map<FruitWeb, Integer> ingredients) {
        this.ingredients = ingredients;
    }
}
