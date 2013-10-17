package no.nith.pg5100;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import no.nith.pg5100.entities.Animal;

public class Client {
    private final EntityManagerFactory entityManagerFactory;

    public Client() {
        entityManagerFactory = Persistence.createEntityManagerFactory("votestuff");

        createAnimals();
    }

    public static void main(String[] args) {
        new Client();
    }

    private void createAnimals() {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        Animal bird = new Animal("Bird");
        Animal fish = new Animal("Fish");
        Animal human = new Animal("Human");

        entityManager.getTransaction().begin();

        entityManager.persist(bird);
        entityManager.persist(fish);
        entityManager.persist(human);

        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
