package no.nith.pg5100;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import no.nith.pg5100.entities.Animal;
import no.nith.pg5100.entities.Voter;

public class Client {
    public static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("votestuff");

    public Client() {
        createAnimals();
    }

    public static void main(String[] args) {
        new Client();

        getVotes();
    }

    private static void getVotes() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            list.add(askForVote());
        }

        for (int id : list) {
            new Voter(id);
        }
    }

    private static int askForVote() {
        Scanner in = new Scanner(System.in);

        System.out.print("What animal do you want to vote for? ");

        return in.nextInt();
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
