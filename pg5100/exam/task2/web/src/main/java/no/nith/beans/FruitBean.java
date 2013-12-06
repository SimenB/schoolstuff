package no.nith.beans;

import no.nith.domain.FruitWeb;
import no.nith.entities.Fruit;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Simen Bekkhus
 */
@ManagedBean(name = "fruitBean")
@SessionScoped
public class FruitBean implements Serializable {
    @EJB(beanName = "FruitEJB")
    private FruitFace fruitEJB;
    private List<FruitWeb> fruits;

    public FruitBean() {
    }

    public List<FruitWeb> getFruits() {
        if (fruits == null) {
            this.fetchFruits();

        }
        return fruits;
    }

    private void fetchFruits() {
        List<Fruit> allFruits = fruitEJB.getAllFruits();
        fruits = new ArrayList<>();

        for (Fruit fruit : allFruits) {
            this.fruits.add(new FruitWeb(fruit.getName(), fruit.getPrice(), fruit.getDescription()));
        }
    }

    public void refetch() {
        fruits = null;
    }
}
