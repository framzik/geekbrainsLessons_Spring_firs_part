package ru.khrebtov;

import org.hibernate.cfg.Configuration;
import ru.khrebtov.repository.EntityManagerFactoryConfig;
import ru.khrebtov.repository.ProductRepository;
import ru.khrebtov.service.ShopService;

import javax.persistence.EntityManagerFactory;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        EntityManagerFactoryConfig config = new EntityManagerFactoryConfig(emFactory);

        ShopService service = new ShopService(config);

        service.findProductsByUserId(2L).forEach(System.out::println);
        service.findUsersByProductId(1L).forEach(System.out::println);
    }
}
