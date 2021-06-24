package ru.khrebtov.hw2.persist;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private final List<Product> productsInCart = new ArrayList<>();

    private final ProductRepository productRepository;

    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProductInCart(Long id) {
        List<Product> products = productRepository.findAll();
        for (Product p : products) {
            if (p.getId().equals(id)) {
                productsInCart.add(p);
                System.out.println(String.format("Product %s will be added in %s", p, this.toString().substring(this.toString().lastIndexOf(".") + 1)));
            }
        }
    }

    public void removeProductInCart(Long id) {
        List<Product> products = productRepository.findAll();
        for (Product p : products) {
            if (p.getId().equals(id)) {
                productsInCart.remove(p);
                System.out.println(String.format("Product  %s will be removed from %s", p, this.toString().substring(this.toString().lastIndexOf(".") + 1)));
            }
        }
    }

}
