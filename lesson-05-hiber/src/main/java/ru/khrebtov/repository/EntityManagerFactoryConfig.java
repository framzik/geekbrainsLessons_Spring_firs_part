package ru.khrebtov.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.function.Consumer;
import java.util.function.Function;

public class EntityManagerFactoryConfig {

    private final EntityManagerFactory emFactory;

    public EntityManagerFactoryConfig(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public EntityManager getEntityManager(){
        return  emFactory.createEntityManager();
    }


    public <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = emFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            em.close();
        }
    }

    public void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
