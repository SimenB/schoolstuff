package no.nith.entities;

/**
 * @author Simen Bekkhus
 */
public class Fruit {
    private String name;
    private float price;
    private String description;

    public Fruit(final String name, final float price, final String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(final float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
