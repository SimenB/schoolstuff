package no.nith.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Simen Bekkhus
 */
@Entity
@Table(name = "FRUIT_IN_SALAD", schema = "BEKSIM_EXAM", catalog = "")
public class FruitInSaladEntity {
    @EmbeddedId
    private FruitInSaladEntityPK fruitInSaladEntityPK;

    @Column(name = "NUMBER_OF_SINGLE_FRUIT", nullable = false, insertable = true, updatable = true, length = 10)
    private int numberOfSingleFruit;

    public FruitInSaladEntityPK getFruitInSaladEntityPK() {
        return fruitInSaladEntityPK;
    }

    public void setFruitInSaladEntityPK(final FruitInSaladEntityPK fruitInSaladEntityPK) {
        this.fruitInSaladEntityPK = fruitInSaladEntityPK;
    }

    public int getNumberOfSingleFruit() {
        return numberOfSingleFruit;
    }

    public void setNumberOfSingleFruit(final int numberOfSingleFruit) {
        this.numberOfSingleFruit = numberOfSingleFruit;
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

    @Override
    public int hashCode() {
        int result = fruitInSaladEntityPK.hashCode();
        result = 31 * result + numberOfSingleFruit;
        return result;
    }
}
