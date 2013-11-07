package no.nith.pg5100.entities;

import com.sun.javafx.beans.annotations.NonNull;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Animal {
    @Id
    @GeneratedValue
    private int id;
    private int numberOfVotes;
    @NonNull
    @Column(unique = true)
    private String name;
    @OneToMany
    private List<Vote> votes;

    public Animal(String name) {
        this.name = name;
    }

    protected Animal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public void incrementVotes() {
        this.numberOfVotes++;
    }
}
