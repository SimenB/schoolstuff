package no.nith.beans;

import no.nith.domain.CustomerWeb;
import no.nith.domain.FruitWeb;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Simen Bekkhus
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    @ManagedProperty("#{fruitBean}")
    private FruitBean fruitBean;
    @EJB(beanName = "FruitEJB")
    private FruitFace fruitEJB;
    private String name;
    private CustomerWeb customer;

    public CustomerWeb getCustomer() {
        return customer;
    }

    public void setFruitBean(FruitBean fruitBean) {
        this.fruitBean = fruitBean;
    }

    public String login() {
        if (customer == null) {
            customer = new CustomerWeb(fruitEJB.createCustomer(this.name));
            fetchIngredients();
        }

        return "showSalads";
    }

    private void fetchIngredients() {
        Map<Integer, Map<String, Integer>> ingredientsForSalads = fruitEJB.fetchIngredientsForSalads(customer.getSaladIds());
        Map<Integer, Map<FruitWeb, Integer>> mappedIngredients = new HashMap<>();

        for (Map.Entry<Integer, Map<String, Integer>> integerMapEntry : ingredientsForSalads.entrySet()) {
            int key = integerMapEntry.getKey();
            Map<FruitWeb, Integer> fruitMap = new HashMap<>();
            Map<String, Integer> stringIntegerMap = ingredientsForSalads.get(key);
            for (String s : stringIntegerMap.keySet()) {
                for (FruitWeb fruit : fruitBean.getFruits()) {
                    if (fruit.getName().equals(s)) {
                        fruitMap.put(fruit, stringIntegerMap.get(s));
                        break;
                    }
                }
            }

            mappedIngredients.put(key, fruitMap);
        }

        customer.setSaladIngredients(mappedIngredients);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
