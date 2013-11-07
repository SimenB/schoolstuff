package no.nith.pg5100.entities;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import no.nith.pg5100.Client;

@Entity
public class Voter {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Vote vote;

    public Voter(int idOfAnimal) {
        final Animal animal = getAnimal(idOfAnimal);

        this.vote = new Vote(this, animal);

        persistVote();
    }

    public Voter() {
    }

    private void persistVote() {

        final EntityManager entityManager = Client.entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(this);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    private Animal getAnimal(int idOfAnimal) {
        final EntityManager entityManager = Client.entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        final Animal animal = entityManager.find(Animal.class, idOfAnimal);

        animal.incrementVotes();

        entityManager.persist(animal);

        entityManager.getTransaction().commit();

        entityManager.close();

        return animal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public void addVote(String name) {

    }
}
