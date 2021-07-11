package ru.khrebtov.service;

import ru.khrebtov.entity.Product;
import ru.khrebtov.entity.User;
import ru.khrebtov.repository.EntityManagerFactoryConfig;

import javax.persistence.EntityManager;
import java.util.List;

public class ShopService {

    private final EntityManagerFactoryConfig emFactory;

    public ShopService(EntityManagerFactoryConfig emFactory) {
        this.emFactory = emFactory;
    }

    public List<Product> findProductsByUserId(Long id) {
        EntityManager em = emFactory.getEntityManager();
        List<Product> products;

        try {
            products = em.createQuery(
                    "select p from Product p left join ProductUser pu on p.id = " +
                            "pu.productsId where pu.usersId=:id",
                    Product.class).setParameter("id", id).getResultList();
        } finally {
            em.close();
        }

        return products;
    }

    public List<User> findUsersByProductId(Long id) {
        EntityManager em = emFactory.getEntityManager();
        List<User> users;

        try {
            users = em.createQuery(
                    "select u from User u left join ProductUser pu on u.id = pu.usersId where pu.productsId=:id",
                    User.class).setParameter("id", id).getResultList();
        } finally {
            em.close();
        }

        return users;
    }
}
