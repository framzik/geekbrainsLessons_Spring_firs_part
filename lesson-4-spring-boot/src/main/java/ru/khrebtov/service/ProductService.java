package ru.khrebtov.service;

import org.springframework.data.domain.Page;
import ru.khrebtov.controller.ProductListParam;
import ru.khrebtov.persist.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Page<Product> findWithFilter(ProductListParam productListParam);

    Optional<Product> findById(Long id);

    void save(Product user);

    void deleteById(Long id);
}
