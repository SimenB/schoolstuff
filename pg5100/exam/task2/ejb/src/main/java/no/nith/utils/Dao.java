package no.nith.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Simen Bekkhus
 */
public final class Dao {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PG5100");

    protected static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
