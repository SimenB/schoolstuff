package no.nith.beans;

import no.nith.entities.Customer;
import no.nith.entities.Fruit;
import no.nith.utils.FruitSaladDAO;

import javax.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Simen Bekkhus
 */
@Stateless(name = "FruitEJB")
public class FruitImpl implements FruitFace {
    @Override
    public List<Fruit> getAllFruits() {
        List<Fruit> fruits = new ArrayList<>();

        Fruit banana = new Fruit("Banana", 15);
        banana.setDescription("yuuuuus");
        fruits.add(banana);

        //return fruits;

        FruitSaladDAO dao = new FruitSaladDAO();

        return dao.getAllFruits();
    }

    @Override
    public int createCustomer(final String name) {
        FruitSaladDAO dao = new FruitSaladDAO();

        Customer customer = dao.createCustomer(name);

        return customer.getCustomerId();
    }
}
