package no.nith.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.io.Serializable;

/**
 * @author Simen Bekkhus
 */
@Embeddable
public class FruitInSaladEntityPK implements Serializable {
    @Column(name = "FK_FRUITINSALAD_FRUITSALAD", nullable = false, length = 10)
    protected int fkFruitinsaladFruitsalad;
    @Column(name = "FK_FRUITINSALAD_FRUIT", nullable = false, length = 10)
    protected int fkFruitinsaladFruit;

    protected FruitInSaladEntityPK() {
    }

    public FruitInSaladEntityPK(final int fkFruitinsaladFruitsalad, final int fkFruitinsaladFruit) {
        this.fkFruitinsaladFruitsalad = fkFruitinsaladFruitsalad;
        this.fkFruitinsaladFruit = fkFruitinsaladFruit;
    }

    public int getFkFruitinsaladFruitsalad() {
        return fkFruitinsaladFruitsalad;
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
