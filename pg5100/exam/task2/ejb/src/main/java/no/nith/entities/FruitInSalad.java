package no.nith.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

/**
 * @author Simen Bekkhus
 */
@Entity
@Table(name = "FRUIT_IN_SALAD")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "FruitInSalad.findAll", query = "SELECT f FROM FruitInSalad f"),
        @NamedQuery(name = "FruitInSalad.findByFkFruitinsaladFruit", query = "SELECT f FROM FruitInSalad f WHERE f.fruitInSaladPK.fkFruitinsaladFruit = :fkFruitinsaladFruit"),
        @NamedQuery(name = "FruitInSalad.findByFkFruitinsaladFruitsalad", query = "SELECT f FROM FruitInSalad f WHERE f.fruitInSaladPK.fkFruitinsaladFruitsalad = :fkFruitinsaladFruitsalad"),
        @NamedQuery(name = "FruitInSalad.findByNumberOfSingleFruit", query = "SELECT f FROM FruitInSalad f WHERE f.numberOfSingleFruit = :numberOfSingleFruit")})
public class FruitInSalad implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FruitInSaladPK fruitInSaladPK;
    @NotNull
    @Column(name = "NUMBER_OF_SINGLE_FRUIT")
    private int numberOfSingleFruit;

    protected FruitInSalad() {
    }

    public FruitInSalad(final FruitInSaladPK fruitInSaladPK, final int numberOfSingleFruit) {
        this.fruitInSaladPK = fruitInSaladPK;
        this.numberOfSingleFruit = numberOfSingleFruit;
    }

    public FruitInSaladPK getFruitInSaladPK() {
        return fruitInSaladPK;
    }

    public void setFruitInSaladPK(FruitInSaladPK fruitInSaladPK) {
        this.fruitInSaladPK = fruitInSaladPK;
    }

    public int getNumberOfSingleFruit() {
        return numberOfSingleFruit;
    }

    public void setNumberOfSingleFruit(int numberOfSingleFruit) {
        this.numberOfSingleFruit = numberOfSingleFruit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fruitInSaladPK != null ? fruitInSaladPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FruitInSalad)) {
            return false;
        }
        FruitInSalad other = (FruitInSalad) object;
        if ((this.fruitInSaladPK == null && other.fruitInSaladPK != null) || (this.fruitInSaladPK != null && !this.fruitInSaladPK.equals(other.fruitInSaladPK))) {
            return false;
        }
        return true;
    }
}
