package ru.khrebtov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.khrebtov.persist.Product;
import ru.khrebtov.persist.ProductRepository;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model) {
        logger.info("Product list page requested");
        model.addAttribute("products", productRepository.findAll());

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
        productRepository.insert(product);

        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model){
        logger.info("Deleting product");
        productRepository.delete(id);

        return "redirect:/products";
    }
}
