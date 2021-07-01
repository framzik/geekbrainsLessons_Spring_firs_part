package ru.khrebtov.persist;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    private Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {
        this.insert(new Product("product1", 10.0d));
        this.insert(new Product("product2", 20.0d));
        this.insert(new Product("product3", 30.0d));
    }

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public Product findById(long id) {
        return productMap.get(id);
    }

    public void insert(Product product) {
        long id;
        if (product.getId() == null) {
            id = identity.incrementAndGet();
            product.setId(id);
        } else id = product.getId();

        productMap.put(id, product);
    }

    public void delete(long id) {
        productMap.remove(id);
    }
}
