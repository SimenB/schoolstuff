package no.nith.domain;

import javax.persistence.Column;
import javax.persistence.Id;

import java.io.Serializable;

/**
 * @author Simen Bekkhus
 */
class FruitInSaladEntityPK implements Serializable {
    @Id
    @Column(name = "SALAD_ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    private int saladId;

    @Id
    @Column(name = "FRUIT_ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    private int fruitId;

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

    @Override
    public int hashCode() {
        int result = saladId;
        result = 31 * result + fruitId;
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FruitInSaladEntityPK that = (FruitInSaladEntityPK) o;

        if (fruitId != that.fruitId) return false;
        if (saladId != that.saladId) return false;

        return true;
    }
}
