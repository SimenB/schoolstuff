package no.nith.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Simen Bekkhus
 */
@Entity
@Table(name = "CUSTOMER")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
        @NamedQuery(name = "Customer.findByCustomerId", query = "SELECT c FROM Customer c WHERE c.customerId = :customerId"),
        @NamedQuery(name = "Customer.findByName", query = "SELECT c FROM Customer c WHERE c.name = :name")})
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME", unique = true)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkFruitsaladCustomer")
    private Collection<FruitSalad> fruitSaladCollection;

    protected Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<FruitSalad> getFruitSaladCollection() {
        return fruitSaladCollection;
    }

    public void setFruitSaladCollection(Collection<FruitSalad> fruitSaladCollection) {
        this.fruitSaladCollection = fruitSaladCollection;
    }

    @Override
    public int hashCode() {
        int result = customerId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (fruitSaladCollection != null ? fruitSaladCollection.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!customerId.equals(customer.customerId)) return false;
        if (fruitSaladCollection != null ? !fruitSaladCollection.equals(customer.fruitSaladCollection) : customer.fruitSaladCollection != null)
            return false;
        if (!name.equals(customer.name)) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Customer[ customerId=" + customerId + " ]";
    }
}
