package no.nith.beans;

import no.nith.entities.FruitEntity;
import no.nith.utils.FruitSaladDAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

/**
 * @author Simen Bekkhus
 */
@Stateless(name = "FruitEJB")
public class FruitImpl implements FruitFace {
    @PersistenceContext(unitName = "PG5100")
    private EntityManager em;

    @Override
    public List<FruitEntity> getAllFruits() {
        FruitSaladDAO dao = new FruitSaladDAO(em);

        return dao.getAllFruits();

        /*List<FruitEntity> fruits = new ArrayList<>();

        FruitEntity banan = new FruitEntity("Banan", 5.50f);
        banan.setDescription("KJEMPEEEEEEEEEEEEEEBRAAAAAAAAAA");
        fruits.add(banan);

        return fruits;*/
    }
}
