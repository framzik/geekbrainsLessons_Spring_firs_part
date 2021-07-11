package ru.khrebtov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.khrebtov.persist.Product;
import ru.khrebtov.persist.ProductRepository;
import ru.khrebtov.persist.ProductSpecification;

import java.util.Optional;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("minCost") Optional<Integer> minCost,
                           @RequestParam("maxCost") Optional<Integer> maxCost,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size) {
        logger.info("Product list page requested");
        Specification<Product> spec = Specification.where(null);

        if (minCost.isPresent()) {
            spec = spec.and(ProductSpecification.minCost(minCost.get()));
        }
        if (maxCost.isPresent()) {
            spec = spec.and(ProductSpecification.maxCost(maxCost.get()));
        }

        model.addAttribute("products", productRepository.findAll(spec,
                PageRequest.of(page.orElse(1) - 1, size.orElse(10))));

        return "products";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        logger.info("New product page requested");
        model.addAttribute("product", new Product());

        return "product_form";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        logger.info("Edit product page requested");
        model.addAttribute("product", productRepository.findById(id));

        return "product_form";
    }

    @PostMapping
    public String update(Product product) {
        logger.info("Saving product");
        productRepository.save(product);

        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        logger.info("Deleting product");
        productRepository.deleteById(id);

        return "redirect:/product";
    }
}
