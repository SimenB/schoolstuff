package no.nith.domain;

/**
 * @author Simen Bekkhus
 */
public class FruitWeb {
    private String name;
    private double price;
    private String description;

    public FruitWeb(final String name, final double price, final String description) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
