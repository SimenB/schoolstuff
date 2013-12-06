package no.nith.domain;

import no.nith.entities.Customer;
import no.nith.entities.FruitSalad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Simen Bekkhus
 */
public class CustomerWeb {
    private int id;
    private String name;
    private List<FruitSaladWeb> salads;
    private FruitSaladWeb selectedSalad;

    public CustomerWeb(Customer customer) {
        this.id = customer.getCustomerId();
        this.name = customer.getName();
        salads = new ArrayList<>();

        if (customer.getFruitSaladCollection() != null) {
            for (FruitSalad fruitSalad : customer.getFruitSaladCollection()) {
                salads.add(new FruitSaladWeb(fruitSalad));
            }
        }
    }

    public List<Integer> getSaladIds() {
        List<Integer> ids = new ArrayList<>();

        for (FruitSaladWeb salad : salads) {
            ids.add(salad.getId());
        }

        return ids;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<FruitSaladWeb> getSalads() {
        return salads;
    }

    public void setSalads(final List<FruitSaladWeb> salads) {
        this.salads = salads;
    }

    public FruitSaladWeb getSelectedSalad() {
        return selectedSalad;
    }

    public void setSelectedSalad(final FruitSaladWeb selectedSalad) {
        this.selectedSalad = selectedSalad;
    }

    public void setSaladIngredients(final Map<Integer, Map<FruitWeb, Integer>> ingredients) {
        for (Map.Entry<Integer, Map<FruitWeb, Integer>> integerMapEntry : ingredients.entrySet()) {
            int fruitId = integerMapEntry.getKey();

            for (FruitSaladWeb salad : salads) {
                if (salad.getId() == fruitId) {
                    salad.setIngredients(integerMapEntry.getValue());
                }
            }
        }
    }
}
