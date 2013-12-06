package no.nith.beans;

import no.nith.entities.Customer;
import no.nith.entities.Fruit;

import javax.ejb.Local;

import java.util.List;
import java.util.Map;

/**
 * @author Simen Bekkhus
 */
@Local
public interface FruitFace {
    List<Fruit> getAllFruits();

    Customer createCustomer(String name);

    Map<Integer, Map<String, Integer>> fetchIngredientsForSalads(List<Integer> saladIds);
}
