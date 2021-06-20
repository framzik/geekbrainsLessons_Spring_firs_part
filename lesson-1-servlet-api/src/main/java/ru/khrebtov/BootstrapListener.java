package ru.khrebtov;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import ru.khrebtov.persist.Product;
import ru.khrebtov.persist.ProductRepository;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        ProductRepository productRepository = new ProductRepository();
        productRepository.save(new Product(1L, "Product 1", 1.0d));
        productRepository.save(new Product(2L, "Product 2", 2.0d));
        productRepository.save(new Product(3L, "Product 3", 3.0d));
        productRepository.save(new Product(4L, "Product 4", 4.0d));
        productRepository.save(new Product(5L, "Product 5", 5.0d));
        productRepository.save(new Product(6L, "Product 6", 6.0d));
        productRepository.save(new Product(7L, "Product 7", 7.0d));
        productRepository.save(new Product(8L, "Product 8", 8.0d));
        productRepository.save(new Product(9L, "Product 9", 9.0d));
        productRepository.save(new Product(10L, "Product 10", 10.01d));
        sc.setAttribute("productRepository", productRepository);
    }
}
