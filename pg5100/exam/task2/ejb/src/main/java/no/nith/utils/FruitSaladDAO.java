package no.nith.utils;

import no.nith.entities.CustomerEntity;
import no.nith.entities.FruitEntity;
import no.nith.entities.FruitInSaladEntity;
import no.nith.entities.FruitInSaladEntityPK;
import no.nith.entities.FruitSaladEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Simen Bekkhus
 */
public class FruitSaladDAO {
   /* @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    *//*@PersistenceContext(unitName = "PG5100")
    private Session session;*//*


    private EntityManager entityManager;

    //private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PG5100");*/

    private EntityManager entityManager;

    public FruitSaladDAO(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public FruitSaladDAO() {
    }

    public CustomerEntity createCustomer(String name) {
        CustomerEntity customer = new CustomerEntity(name);
        //EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        entityManager.close();

        return customer;
    }

    public FruitEntity createFruit(String name, float price, String description) {
        FruitEntity fruit = new FruitEntity(name, price);
        fruit.setDescription(description);
        //EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(fruit);
        entityManager.getTransaction().commit();
        entityManager.close();

        return fruit;
    }

    public FruitSaladEntity createFruitSalad(CustomerEntity customer, String nameOfSalad,
                                             ArrayList<FruitEntity> ingredients, ArrayList<Integer> numberOfIngredients,
                                             String instructions) {
        //EntityManager entityManager = entityManagerFactory.createEntityManager();
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
        Session session = HibernateUtils.getSessionFactory().openSession();

        Query query = session.createQuery("select price from FruitEntity where fruitId = :fruitId");

        query.setParameter("fruitId", fruitInSalad.getFruitInSaladEntityPK().getFkFruitinsaladFruit());

        List list = query.list();

        session.close();

        totalPrice += fruitInSalad.getNumberOfSingleFruit() * ((float) list.get(0));

        return totalPrice;
    }

    public List<FruitEntity> getAllFruits() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Query query = session.createQuery("from FruitEntity");

        List<FruitEntity> fruits = (List<FruitEntity>) query.list();

        session.close();

        return fruits;
    }
}
