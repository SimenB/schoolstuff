package no.nith.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * @author Simen Bekkhus
 */
@Embeddable
public class FruitInSaladPK implements Serializable {
    @NotNull
    @Column(name = "FK_FRUITINSALAD_FRUIT")
    private int fkFruitinsaladFruit;
    @NotNull
    @Column(name = "FK_FRUITINSALAD_FRUITSALAD")
    private int fkFruitinsaladFruitsalad;

    protected FruitInSaladPK() {
    }

    public FruitInSaladPK(int fkFruitinsaladFruit, int fkFruitinsaladFruitsalad) {
        this.fkFruitinsaladFruit = fkFruitinsaladFruit;
        this.fkFruitinsaladFruitsalad = fkFruitinsaladFruitsalad;
    }

    public int getFkFruitinsaladFruit() {
        return fkFruitinsaladFruit;
    }

    public int getFkFruitinsaladFruitsalad() {
        return fkFruitinsaladFruitsalad;
    }

    @Override
    public int hashCode() {
        int result = fkFruitinsaladFruit;
        result = 31 * result + fkFruitinsaladFruitsalad;
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FruitInSaladPK that = (FruitInSaladPK) o;

        if (fkFruitinsaladFruit != that.fkFruitinsaladFruit) return false;
        if (fkFruitinsaladFruitsalad != that.fkFruitinsaladFruitsalad) return false;

        return true;
    }

    @Override
    public String toString() {
        return "FruitInSaladPK[ fkFruitinsaladFruit=" + fkFruitinsaladFruit + ", fkFruitinsaladFruitsalad=" + fkFruitinsaladFruitsalad + " ]";
    }
}
