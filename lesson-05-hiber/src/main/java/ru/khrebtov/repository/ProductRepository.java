package ru.khrebtov.repository;

import ru.khrebtov.entity.Product;

import java.util.List;
import java.util.Optional;

public class ProductRepository {

    private final EntityManagerFactoryConfig emFactory;

    public ProductRepository(EntityManagerFactoryConfig emFactory) {
        this.emFactory = emFactory;
    }

    public Optional<Product> findById(Long id) {
        return emFactory.executeForEntityManager(
                em -> Optional.ofNullable(em.find(Product.class, id))
        );
    }

    public List<Product> findAll() {
        return emFactory.executeForEntityManager(
                em -> em.createQuery("select u from Product u", Product.class).getResultList()
        );
    }

    public void deleteById(Long id) {
        emFactory.executeInTransaction(
                em -> em.createQuery("delete from Product where id = :id")
                        .setParameter("id", id)
                        .executeUpdate()
        );
    }

    public void insert(Product product) {
        emFactory.executeInTransaction(
                em -> em.persist(product)
        );
    }

    public void update(Product product) {
        emFactory.executeInTransaction(
                em -> em.merge(product)
        );
    }

    public void save(Product product) {
        if (product.getId() == null) {
            insert(product);
        } else {
            update(product);
        }
    }
}
