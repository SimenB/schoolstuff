package no.nith.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

/**
 * @author Simen Bekkhus
 */
@Entity
@Table(name = "FRUIT", schema = "BEKSIM_EXAM", catalog = "")
public class FruitEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FRUIT_ID", nullable = false, updatable = false, length = 10)
    private int fruitId;
    @Column(name = "NAME", nullable = false, length = 32, unique = true)
    private String name;
    @Column(name = "PRICE", nullable = false, length = 23)
    private float price;
    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    public FruitEntity(final String name, final float price) {
        this.name = name;
        this.price = price;
    }

    protected FruitEntity() {
    }

    public int getFruitId() {
        return fruitId;
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

    @Override
    public int hashCode() {
        int result = fruitId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FruitEntity that = (FruitEntity) o;

        if (fruitId != that.fruitId) return false;
        if (Float.compare(that.price, price) != 0) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }
}
