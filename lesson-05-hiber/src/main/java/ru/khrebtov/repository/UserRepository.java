package ru.khrebtov.repository;

import ru.khrebtov.entity.User;

import java.util.List;
import java.util.Optional;

public class UserRepository {

    private final EntityManagerFactoryConfig emFactory;

    public UserRepository(EntityManagerFactoryConfig emFactory) {
        this.emFactory = emFactory;
    }

    public List<User> findAll() {
        return emFactory.executeForEntityManager(
                em -> em.createQuery("select u from User u", User.class).getResultList()
        );
    }

    public Optional<User> findById(long id) {
        return  emFactory.executeForEntityManager(
                em -> Optional.ofNullable(em.find(User.class, id))
        );
    }

    public void insert(User user) {
        emFactory.executeInTransaction(
                em -> em.persist(user)
        );
    }

    public void update(User user) {
        emFactory.executeInTransaction(
                em -> em.merge(user)
        );
    }

    public void delete(long id) {
        emFactory.executeInTransaction(
                em -> em.createQuery("delete from User where id = :id")
                        .setParameter("id", id)
                        .executeUpdate()
        );
    }

    public void save(User user) {
        if (user.getId() == null) {
            insert(user);
        } else {
            update(user);
        }
    }
}
