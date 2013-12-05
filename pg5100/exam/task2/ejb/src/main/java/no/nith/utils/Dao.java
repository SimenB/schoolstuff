package no.nith.utils;

import no.nith.domain.CustomerEntity;
import no.nith.domain.FruitEntity;
import no.nith.domain.FruitInSaladEntity;
import no.nith.domain.FruitInSaladEntityPK;
import no.nith.domain.FruitSaladEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Simen Bekkhus
 */
public class Dao {
    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PG5100");
    public static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException he) {
            System.err.println("Error creating Session: " + he);
            throw new ExceptionInInitializerError(he);
        }
    }

    public CustomerEntity createCustomer(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CustomerEntity customer = new CustomerEntity(name);

        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        entityManager.close();

        return customer;
    }

    public FruitEntity createFruit(String name, float price, String description) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        FruitEntity fruit = new FruitEntity(name, price);

        fruit.setDescription(description);

        entityManager.getTransaction().begin();
        entityManager.persist(fruit);
        entityManager.getTransaction().commit();
        entityManager.close();

        return fruit;
    }

    public FruitSaladEntity createFruitSalad(CustomerEntity customer, String nameOfSalad,
                                             ArrayList<FruitEntity> ingredients, ArrayList<Integer> numberOfIngredients,
                                             String instructions) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        float totalPrice = 0;

        FruitSaladEntity saladEntity = new FruitSaladEntity(nameOfSalad, customer);

        saladEntity.setInstructions(instructions);

        entityManager.getTransaction().begin();

        for (int i = 0; i < ingredients.size(); i++) {
            FruitInSaladEntityPK fruitInSaladEntityPK = new FruitInSaladEntityPK(saladEntity.getSaladId(), ingredients.get(i).getFruitId());
            FruitInSaladEntity fruitInSaladEntity = new FruitInSaladEntity(fruitInSaladEntityPK, numberOfIngredients.get(i));

            entityManager.persist(fruitInSaladEntity);

            totalPrice += this.getPriceOfIngredient(fruitInSaladEntity);
        }

        saladEntity.setPrice(totalPrice);

        entityManager.persist(saladEntity);
        entityManager.getTransaction().commit();
        entityManager.close();

        return saladEntity;
    }

    private float getPriceOfIngredient(final FruitInSaladEntity fruitInSalad) {
        float totalPrice = 0;

        Session session = sessionFactory.openSession();

        Query query = session.createQuery("select price from FruitEntity where fruitId = :fruitId");

        query.setParameter("fruitId", fruitInSalad.getFruitInSaladEntityPK().getFkFruitinsaladFruit());

        List list = query.list();

        session.close();

        totalPrice += fruitInSalad.getNumberOfSingleFruit() * ((float) list.get(0));

        return totalPrice;
    }
}
