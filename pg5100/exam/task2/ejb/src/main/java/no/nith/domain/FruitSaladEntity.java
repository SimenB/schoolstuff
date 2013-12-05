package no.nith.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Collection;

/**
 * @author Simen Bekkhus
 */
@Entity
@Table(name = "FRUIT_SALAD", schema = "BEKSIM_EXAM", catalog = "")
public class FruitSaladEntity {
    @Id
    @GeneratedValue
    @Column(name = "SALAD_ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    private int saladId;

    @Column(name = "CUSTOMER_ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    private int customerId;

    @Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
    private String name;

    @Column(name = "PRICE", nullable = false, insertable = true, updatable = true, length = 23, precision = 0)
    private float price;

    @OneToMany(mappedBy = "fruitSaladBySaladId")
    private Collection<FruitInSaladEntity> fruitInSaladsBySaladId;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID", nullable = false)
    private CustomerEntity customerByCustomerId;

    public int getSaladId() {
        return saladId;
    }

    public void setSaladId(final int saladId) {
        this.saladId = saladId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final int customerId) {
        this.customerId = customerId;
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

    public Collection<FruitInSaladEntity> getFruitInSaladsBySaladId() {
        return fruitInSaladsBySaladId;
    }

    public void setFruitInSaladsBySaladId(final Collection<FruitInSaladEntity> fruitInSaladsBySaladId) {
        this.fruitInSaladsBySaladId = fruitInSaladsBySaladId;
    }

    public CustomerEntity getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(final CustomerEntity customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }

    @Override
    public int hashCode() {
        int result = saladId;
        result = 31 * result + customerId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FruitSaladEntity that = (FruitSaladEntity) o;

        if (customerId != that.customerId) return false;
        if (Float.compare(that.price, price) != 0) return false;
        if (saladId != that.saladId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }
}
