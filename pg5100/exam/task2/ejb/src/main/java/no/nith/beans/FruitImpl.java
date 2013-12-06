package no.nith.beans;

import no.nith.entities.Customer;
import no.nith.entities.Fruit;
import no.nith.utils.FruitSaladDAO;

import javax.ejb.Stateless;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Simen Bekkhus
 */
@Stateless(name = "FruitEJB")
public class FruitImpl implements FruitFace {
    @Override
    public List<Fruit> getAllFruits() {
        return new FruitSaladDAO().getAllFruits();
    }

    @Override
    public Customer createCustomer(final String name) {
        return new FruitSaladDAO().createCustomer(name);
    }

    @Override
    public Map<Integer, Map<String, Integer>> fetchIngredientsForSalads(final List<Integer> saladIds) {
        Map<Integer, Map<String, Integer>> amountOfEachFruit = new HashMap<>();
        FruitSaladDAO dao = new FruitSaladDAO();

        for (int id : saladIds) {
            amountOfEachFruit.put(id, dao.getIngredientsInSalads(id));
        }

        return amountOfEachFruit;
    }
}
