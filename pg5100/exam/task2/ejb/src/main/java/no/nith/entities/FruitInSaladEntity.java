package no.nith.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;

/**
 * @author Simen Bekkhus
 */
@Entity
@Table(name = "FRUIT_IN_SALAD", schema = "BEKSIM_EXAM", catalog = "")
public class FruitInSaladEntity implements Serializable {
    @EmbeddedId
    private FruitInSaladEntityPK fruitInSaladEntityPK;
    @Column(name = "NUMBER_OF_SINGLE_FRUIT", nullable = false, length = 10)
    private int numberOfSingleFruit;

    protected FruitInSaladEntity() {
    }

    public FruitInSaladEntity(final FruitInSaladEntityPK fruitInSaladEntityPK, final int numberOfSingleFruit) {
        this.fruitInSaladEntityPK = fruitInSaladEntityPK;
        this.numberOfSingleFruit = numberOfSingleFruit;
    }

    public FruitInSaladEntityPK getFruitInSaladEntityPK() {
        return fruitInSaladEntityPK;
    }

    public int getNumberOfSingleFruit() {
        return numberOfSingleFruit;
    }

    public void setNumberOfSingleFruit(final int numberOfSingleFruit) {
        this.numberOfSingleFruit = numberOfSingleFruit;
    }

    @Override
    public int hashCode() {
        int result = fruitInSaladEntityPK.hashCode();
        result = 31 * result + numberOfSingleFruit;
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FruitInSaladEntity that = (FruitInSaladEntity) o;

        if (numberOfSingleFruit != that.numberOfSingleFruit) return false;
        if (!fruitInSaladEntityPK.equals(that.fruitInSaladEntityPK)) return false;

        return true;
    }
}
