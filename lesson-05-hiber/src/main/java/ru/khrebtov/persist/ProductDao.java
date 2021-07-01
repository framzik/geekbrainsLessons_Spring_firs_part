package ru.khrebtov.persist;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

import static java.util.Objects.isNull;

public class ProductDao {

    EntityManagerFactory emFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    public Product findById(Long id) {
        EntityManager em = emFactory.createEntityManager();
        Product product = em.find(Product.class, id);
        em.close();

        return product;
    }

    public List<Product> findAll() {
        EntityManager em = emFactory.createEntityManager();
        List<Product> getAllProducts = em.createNamedQuery("getAllProducts", Product.class).getResultList();
        em.close();

        return getAllProducts;
    }

    public void deleteById(Long id) {
        EntityManager em = emFactory.createEntityManager();
        Product entity = em.find(Product.class, id);

        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();

        em.close();
    }

    public Product saveOrUpdate(Product product) {
        EntityManager em = emFactory.createEntityManager();

        Product newProduct = product;
        if (isNull(product.getId())) {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        } else {
            em.getTransaction().begin();
            newProduct = em.merge(product);
            em.getTransaction().commit();
        }

        em.close();

        return newProduct;
    }
}
