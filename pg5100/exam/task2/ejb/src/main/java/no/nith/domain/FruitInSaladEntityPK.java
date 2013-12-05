package no.nith.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import java.io.Serializable;

/**
 * @author Simen Bekkhus
 */
@Embeddable
public class FruitInSaladEntityPK implements Serializable {
    @Column(name = "FK_FRUITINSALAD_FRUITSALAD", nullable = false, insertable = true, updatable = true, length = 10)
    protected int fkFruitinsaladFruitsalad;

    @Column(name = "FK_FRUITINSALAD_FRUIT", nullable = false, insertable = true, updatable = true, length = 10)
    protected int fkFruitinsaladFruit;

    public int getFkFruitinsaladFruitsalad() {
        return fkFruitinsaladFruitsalad;
    }

    public void setFkFruitinsaladFruitsalad(final int fkFruitinsaladFruitsalad) {
        this.fkFruitinsaladFruitsalad = fkFruitinsaladFruitsalad;
    }

    public int getFkFruitinsaladFruit() {
        return fkFruitinsaladFruit;
    }

    public void setFkFruitinsaladFruit(final int fkFruitinsaladFruit) {
        this.fkFruitinsaladFruit = fkFruitinsaladFruit;
    }

    @Override
    public int hashCode() {
        int result = fkFruitinsaladFruitsalad;
        result = 31 * result + fkFruitinsaladFruit;
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FruitInSaladEntityPK that = (FruitInSaladEntityPK) o;

        if (fkFruitinsaladFruit != that.fkFruitinsaladFruit) return false;
        if (fkFruitinsaladFruitsalad != that.fkFruitinsaladFruitsalad) return false;

        return true;
    }
}
