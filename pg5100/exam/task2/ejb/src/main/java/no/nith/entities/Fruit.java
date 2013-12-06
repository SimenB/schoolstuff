package no.nith.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

/**
 * @author Simen Bekkhus
 */
@Entity
@Table(name = "FRUIT")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Fruit.findAll", query = "SELECT f FROM Fruit f"),
        @NamedQuery(name = "Fruit.findByFruitId", query = "SELECT f FROM Fruit f WHERE f.fruitId = :fruitId"),
        @NamedQuery(name = "Fruit.findByDescription", query = "SELECT f FROM Fruit f WHERE f.description = :description"),
        @NamedQuery(name = "Fruit.findByName", query = "SELECT f FROM Fruit f WHERE f.name = :name"),
        @NamedQuery(name = "Fruit.findByPrice", query = "SELECT f FROM Fruit f WHERE f.price = :price")})
public class Fruit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FRUIT_ID")
    private Integer fruitId;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "NAME", unique = true)
    private String name;
    @NotNull
    @Column(name = "PRICE")
    private double price;

    protected Fruit() {
    }

    public Fruit(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Fruit(final String name, final double price, final String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Integer getFruitId() {
        return fruitId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = fruitId.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fruit fruit = (Fruit) o;

        if (Double.compare(fruit.price, price) != 0) return false;
        if (description != null ? !description.equals(fruit.description) : fruit.description != null) return false;
        if (!fruitId.equals(fruit.fruitId)) return false;
        if (!name.equals(fruit.name)) return false;

        return true;
    }
}
