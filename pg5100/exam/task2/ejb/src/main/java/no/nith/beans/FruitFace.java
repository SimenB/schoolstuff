package no.nith.beans;

import no.nith.entities.Fruit;

import javax.ejb.Local;

import java.util.List;

/**
 * @author Simen Bekkhus
 */
@Local
public interface FruitFace {
    List<Fruit> getAllFruits();

    int createCustomer(String name);
}
