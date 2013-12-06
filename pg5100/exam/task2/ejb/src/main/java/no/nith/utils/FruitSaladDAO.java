package no.nith.utils;

import no.nith.entities.Customer;
import no.nith.entities.Fruit;
import no.nith.entities.FruitInSalad;
import no.nith.entities.FruitInSaladPK;
import no.nith.entities.FruitSalad;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Simen Bekkhus
 */
public class FruitSaladDAO {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PG5100");

    public FruitSaladDAO() {
    }

    public Customer createCustomer(String name) {
        Customer customer = new Customer(name);
        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        entityManager.close();

        return customer;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public Fruit createFruit(String name, float price, String description) {
        Fruit fruit = new Fruit(name, price);
        fruit.setDescription(description);
        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(fruit);
        entityManager.getTransaction().commit();
        entityManager.close();

        return fruit;
    }

    public FruitSalad createFruitSalad(Customer customer, String nameOfSalad,
                                       ArrayList<Fruit> ingredients, ArrayList<Integer> numberOfIngredients,
                                       String instructions) {
        EntityManager entityManager = getEntityManager();
        float totalPrice = 0;

        FruitSalad salad = new FruitSalad(nameOfSalad, customer);

        salad.setInstructions(instructions);
        entityManager.getTransaction().begin();

        for (int i = 0; i < ingredients.size(); i++) {
            FruitInSaladPK fruitInSaladPK = new FruitInSaladPK(salad.getSaladId(), ingredients.get(i).getFruitId());
            FruitInSalad fruitInSalad = new FruitInSalad(fruitInSaladPK, numberOfIngredients.get(i));

            entityManager.persist(fruitInSalad);

            totalPrice += this.getPriceOfIngredient(fruitInSalad);
        }

        salad.setPrice(totalPrice);

        entityManager.persist(salad);
        entityManager.getTransaction().commit();
        entityManager.close();

        return salad;
    }

    private float getPriceOfIngredient(final FruitInSalad fruitInSalad) {
        float totalPrice = 0;
        EntityManager entityManager = getEntityManager();

        List results = entityManager.createNamedQuery("Fruit.findByFruitId")
                .setParameter("fruitId", fruitInSalad.getFruitInSaladPK().getFkFruitinsaladFruit())
                .getResultList();

        totalPrice += fruitInSalad.getNumberOfSingleFruit() * ((float) results.get(0));

        return totalPrice;
    }

    public List<Fruit> getAllFruits() {
        return getEntityManager().createNamedQuery("Fruit.findAll", Fruit.class).getResultList();
    }
}
