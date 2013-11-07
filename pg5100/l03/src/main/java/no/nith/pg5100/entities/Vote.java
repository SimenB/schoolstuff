package no.nith.pg5100.entities;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import no.nith.pg5100.Client;

@Entity
public class Vote {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Voter voter;
    @OneToOne
    private Animal animal;

    public Vote(Voter voter, Animal animal) {
        this.voter = voter;
        this.animal = animal;

        persist();
    }

    public Vote() {
    }

    private void persist() {
        final EntityManager entityManager = Client.entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(this);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
