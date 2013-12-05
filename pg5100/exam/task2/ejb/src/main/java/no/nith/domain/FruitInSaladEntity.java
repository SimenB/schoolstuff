package no.nith.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Simen Bekkhus
 */
@Entity
@Table(name = "FRUIT_IN_SALAD", schema = "BEKSIM_EXAM", catalog = "")
@IdClass(FruitInSaladEntityPK.class)
public class FruitInSaladEntity {
    @Id
    @Column(name = "SALAD_ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    private int saladId;

    @Id
    @Column(name = "FRUIT_ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    private int fruitId;

    @Column(name = "NUMBER_OF_SINGLE_FRUIT", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    private int numberOfSingleFruit;

    @ManyToOne
    @JoinColumn(name = "FRUIT_ID", referencedColumnName = "FRUIT_ID", nullable = false)
    private FruitEntity fruitByFruitId;

    @ManyToOne
    @JoinColumn(name = "SALAD_ID", referencedColumnName = "SALAD_ID", nullable = false)
    private FruitSaladEntity fruitSaladBySaladId;

    public int getSaladId() {
        return saladId;
    }

    public void setSaladId(final int saladId) {
        this.saladId = saladId;
    }

    public int getFruitId() {
        return fruitId;
    }

    public void setFruitId(final int fruitId) {
        this.fruitId = fruitId;
    }

    public int getNumberOfSingleFruit() {
        return numberOfSingleFruit;
    }

    public void setNumberOfSingleFruit(final int numberOfSingleFruit) {
        this.numberOfSingleFruit = numberOfSingleFruit;
    }

    public FruitEntity getFruitByFruitId() {
        return fruitByFruitId;
    }

    public void setFruitByFruitId(final FruitEntity fruitByFruitId) {
        this.fruitByFruitId = fruitByFruitId;
    }

    public FruitSaladEntity getFruitSaladBySaladId() {
        return fruitSaladBySaladId;
    }

    public void setFruitSaladBySaladId(final FruitSaladEntity fruitSaladBySaladId) {
        this.fruitSaladBySaladId = fruitSaladBySaladId;
    }

    @Override
    public int hashCode() {
        int result = saladId;
        result = 31 * result + fruitId;
        result = 31 * result + numberOfSingleFruit;
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FruitInSaladEntity that = (FruitInSaladEntity) o;

        if (fruitId != that.fruitId) return false;
        if (numberOfSingleFruit != that.numberOfSingleFruit) return false;
        if (saladId != that.saladId) return false;

        return true;
    }
}
