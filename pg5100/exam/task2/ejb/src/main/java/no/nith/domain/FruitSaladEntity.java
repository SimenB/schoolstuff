package no.nith.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.io.Serializable;

/**
 * @author Simen Bekkhus
 */
@Entity
@Table(name = "FRUIT_SALAD", schema = "BEKSIM_EXAM", catalog = "")
public class FruitSaladEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SALAD_ID", nullable = false, insertable = true, updatable = true, length = 10)
    private int saladId;
    @Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 255)
    private String name;
    @Column(name = "PRICE", nullable = false, insertable = true, updatable = true, length = 23)
    private float price;
    @Basic
    @Column(name = "INSTRUCTIONS", nullable = true, insertable = true, updatable = true, length = 1023)
    private String instructions;
    @ManyToOne
    @JoinColumn(name = "FK_FRUITSALAD_CUSTOMER", referencedColumnName = "CUSTOMER_ID", nullable = false)
    private CustomerEntity customerByFkFruitsaladCustomer;

    protected FruitSaladEntity() {
    }

    public FruitSaladEntity(final String name, final CustomerEntity customerByFkFruitsaladCustomer) {
        this.name = name;
        this.customerByFkFruitsaladCustomer = customerByFkFruitsaladCustomer;
    }

    public int getSaladId() {
        return saladId;
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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(final String instructions) {
        this.instructions = instructions;
    }

    public CustomerEntity getCustomerByFkFruitsaladCustomer() {
        return customerByFkFruitsaladCustomer;
    }

    public void setCustomerByFkFruitsaladCustomer(final CustomerEntity customerByFkFruitsaladCustomer) {
        this.customerByFkFruitsaladCustomer = customerByFkFruitsaladCustomer;
    }

    @Override
    public int hashCode() {
        int result = saladId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (instructions != null ? instructions.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FruitSaladEntity that = (FruitSaladEntity) o;

        if (Float.compare(that.price, price) != 0) return false;
        if (saladId != that.saladId) return false;
        if (instructions != null ? !instructions.equals(that.instructions) : that.instructions != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }
}
