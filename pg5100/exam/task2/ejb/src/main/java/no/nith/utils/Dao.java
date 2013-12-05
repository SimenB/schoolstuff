package no.nith.utils;

import no.nith.domain.CustomerEntity;
import no.nith.domain.FruitEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Simen Bekkhus
 */
public class Dao {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PG5100");

    public CustomerEntity createCustomer(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CustomerEntity customer = new CustomerEntity();

        customer.setName(name);

        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        entityManager.close();

        return customer;
    }

    public FruitEntity createFruit(String name, float price, String description) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        FruitEntity fruit = new FruitEntity();

        fruit.setName(name);
        fruit.setPrice(price);
        fruit.setDescription(description);

        entityManager.getTransaction().begin();
        entityManager.persist(fruit);
        entityManager.getTransaction().commit();
        entityManager.close();

        return fruit;
    }
}
