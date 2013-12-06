package no.nith.utils;

import no.nith.entities.Customer;
import no.nith.entities.Fruit;
import no.nith.entities.FruitInSalad;
import no.nith.entities.FruitInSaladPK;
import no.nith.entities.FruitSalad;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Simen Bekkhus
 */
public class FruitSaladDAO {
    public FruitSaladDAO() {
    }

    public Customer createCustomer(String name) {
        EntityManager entityManager = Dao.getEntityManager();
        Customer customer;

        try {
            return entityManager.createNamedQuery("Customer.findByName", Customer.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            customer = new Customer(name);

            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            entityManager.close();

            return customer;
        }
    }

    public Fruit createFruit(String name, float price, String description) {
        Fruit fruit = new Fruit(name, price);
        fruit.setDescription(description);
        EntityManager entityManager = Dao.getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(fruit);
        entityManager.getTransaction().commit();
        entityManager.close();

        return fruit;
    }

    public FruitSalad createFruitSalad(Customer customer, String nameOfSalad,
                                       ArrayList<Fruit> ingredients, ArrayList<Integer> numberOfIngredients,
                                       String instructions) {
        EntityManager entityManager = Dao.getEntityManager();
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
        EntityManager entityManager = Dao.getEntityManager();

        Float result = entityManager.createNamedQuery("Fruit.findByFruitId", Float.class)
                .setParameter("fruitId", fruitInSalad.getFruitInSaladPK().getFkFruitinsaladFruit())
                .getSingleResult();

        totalPrice += fruitInSalad.getNumberOfSingleFruit() * result;

        return totalPrice;
    }

    public List<Fruit> getAllFruits() {
        return Dao.getEntityManager().createNamedQuery("Fruit.findAll", Fruit.class).getResultList();
    }

    public Map<String, Integer> getIngredientsInSalads(final int id) {
        List<FruitInSalad> resultList = Dao.getEntityManager().createNamedQuery("FruitInSalad.findByFkFruitinsaladFruitsalad", FruitInSalad.class)
                .setParameter("fkFruitinsaladFruitsalad", id)
                .getResultList();

        Map<String, Integer> ingredientsMap = new HashMap<>();

        for (FruitInSalad fruitInSalad : resultList) {
            Fruit singleFruit = Dao.getEntityManager().createNamedQuery("Fruit.findByFruitId", Fruit.class)
                    .setParameter("fruitId", fruitInSalad.getFruitInSaladPK().getFkFruitinsaladFruit())
                    .getSingleResult();

            ingredientsMap.put(singleFruit.getName(), fruitInSalad.getNumberOfSingleFruit());
        }

        return ingredientsMap;
    }
}
