package no.nith.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

/**
 * @author Simen Bekkhus
 */
@Entity
@Table(name = "FRUIT_SALAD", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"NAME", "FK_FRUITSALAD_CUSTOMER"})
})
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "FruitSalad.findAll", query = "SELECT f FROM FruitSalad f"),
        @NamedQuery(name = "FruitSalad.findBySaladId", query = "SELECT f FROM FruitSalad f WHERE f.saladId = :saladId"),
        @NamedQuery(name = "FruitSalad.findByInstructions", query = "SELECT f FROM FruitSalad f WHERE f.instructions = :instructions"),
        @NamedQuery(name = "FruitSalad.findByName", query = "SELECT f FROM FruitSalad f WHERE f.name = :name"),
        @NamedQuery(name = "FruitSalad.findByPrice", query = "SELECT f FROM FruitSalad f WHERE f.price = :price")})
public class FruitSalad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SALAD_ID")
    private Integer saladId;
    @Size(max = 1023)
    @Column(name = "INSTRUCTIONS")
    private String instructions;
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name;
    @NotNull
    @Column(name = "PRICE")
    private double price;
    @JoinColumn(name = "FK_FRUITSALAD_CUSTOMER", referencedColumnName = "CUSTOMER_ID")
    @ManyToOne(optional = false)
    private Customer fkFruitsaladCustomer;

    protected FruitSalad() {
    }

    public FruitSalad(final String name, final Customer fkFruitsaladCustomer) {
        this.name = name;
        this.fkFruitsaladCustomer = fkFruitsaladCustomer;
    }

    public FruitSalad(final String name, final double price, final Customer fkFruitsaladCustomer) {
        this.name = name;
        this.price = price;
        this.fkFruitsaladCustomer = fkFruitsaladCustomer;
    }

    public Integer getSaladId() {
        return saladId;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Customer getFkFruitsaladCustomer() {
        return fkFruitsaladCustomer;
    }

    public void setFkFruitsaladCustomer(Customer fkFruitsaladCustomer) {
        this.fkFruitsaladCustomer = fkFruitsaladCustomer;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = saladId.hashCode();
        result = 31 * result + (instructions != null ? instructions.hashCode() : 0);
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + fkFruitsaladCustomer.hashCode();
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FruitSalad that = (FruitSalad) o;

        if (Double.compare(that.price, price) != 0) return false;
        if (!fkFruitsaladCustomer.equals(that.fkFruitsaladCustomer)) return false;
        if (instructions != null ? !instructions.equals(that.instructions) : that.instructions != null) return false;
        if (!name.equals(that.name)) return false;
        if (!saladId.equals(that.saladId)) return false;

        return true;
    }

    @Override
    public String toString() {
        return "FruitSalad[ saladId=" + saladId + " ]";
    }
}
