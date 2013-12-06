package no.nith.beans;

import no.nith.entities.Fruit;
import no.nith.entities.FruitEntity;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Simen Bekkhus
 */
@Named("fruit")
@SessionScoped
@ManagedBean
public class FruitBean implements Serializable {
    @EJB(beanName = "FruitEJB")
    private FruitFace fruitEJB;
    private List<Fruit> fruits;

    public List<Fruit> getFruits() {
        if (fruits == null) {
            this.fetchFruits();
        }
        return fruits;
    }

    public void refetch() {
        fruits = null;
    }

    private void fetchFruits() {
        List<FruitEntity> allFruits = fruitEJB.getAllFruits();
        fruits = new ArrayList<>();

        for (FruitEntity fruit : allFruits) {
            this.fruits.add(new Fruit(fruit.getName(), fruit.getPrice(), fruit.getDescription()));
        }
    }
}
