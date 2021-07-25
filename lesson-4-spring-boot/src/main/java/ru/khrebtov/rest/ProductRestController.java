package ru.khrebtov.rest;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.khrebtov.controller.NotFoundException;
import ru.khrebtov.controller.ProductListParam;
import ru.khrebtov.persist.Product;
import ru.khrebtov.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(path = "/filter", produces = "application/json")
    public Page<Product> findWithFilter(ProductListParam productListParam) {
        return productService.findWithFilter(productListParam);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Product findById(@PathVariable("id") Long id) {
        return productService.findById(id)
                             .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @PostMapping(produces = "application/json")
    public Product create(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new BadRequestException("User Id should be null");
        }
        productService.save(product);
        return product;
    }

    @PutMapping(produces = "application/json")
    public void update(@RequestBody Product product) {
        if (product.getId() == null) {
            throw new BadRequestException("User Id shouldn't be null");
        }
        productService.save(product);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public void delete(@PathVariable("id") Long id) {
        productService.deleteById(id);
    }
}
