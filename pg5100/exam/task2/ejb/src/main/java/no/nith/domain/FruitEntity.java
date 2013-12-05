package no.nith.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Collection;

/**
 * @author Simen Bekkhus
 */
@Entity
@Table(name = "FRUIT", schema = "BEKSIM_EXAM", catalog = "")
public class FruitEntity {
    @Id
    @GeneratedValue
    @Column(name = "FRUIT_ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    private int fruitId;

    @Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
    private String name;

    @Column(name = "PRICE", nullable = false, insertable = true, updatable = true, length = 23, precision = 0)
    private float price;

    @OneToMany(mappedBy = "fruitByFruitId")
    private Collection<FruitInSaladEntity> fruitInSaladsByFruitId;

    public int getFruitId() {
        return fruitId;
    }

    public void setFruitId(final int fruitId) {
        this.fruitId = fruitId;
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

    public Collection<FruitInSaladEntity> getFruitInSaladsByFruitId() {
        return fruitInSaladsByFruitId;
    }

    public void setFruitInSaladsByFruitId(final Collection<FruitInSaladEntity> fruitInSaladsByFruitId) {
        this.fruitInSaladsByFruitId = fruitInSaladsByFruitId;
    }

    @Override
    public int hashCode() {
        int result = fruitId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FruitEntity that = (FruitEntity) o;

        if (fruitId != that.fruitId) return false;
        if (Float.compare(that.price, price) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }
}
