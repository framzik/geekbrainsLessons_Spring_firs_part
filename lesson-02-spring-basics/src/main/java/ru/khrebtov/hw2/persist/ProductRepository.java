package ru.khrebtov.hw2.persist;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    {
        products.add(new Product(1L, "Product 1", 1.0d));
        products.add(new Product(2L, "Product 1", 2.0d));
        products.add(new Product(3L, "Product 1", 3.0d));
        products.add(new Product(4L, "Product 1", 4.0d));
        products.add(new Product(5L, "Product 1", 5.0d));
    }

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(Long id) {
        for (Product p : products) {
            if (p.getId().equals(id)) return Optional.of(p);
        }
        return Optional.empty();
    }
}
