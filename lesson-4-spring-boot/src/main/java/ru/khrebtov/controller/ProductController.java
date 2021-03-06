package ru.khrebtov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.khrebtov.persist.Product;
import ru.khrebtov.persist.User;
import ru.khrebtov.service.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listPage(Model model,
                           ProductListParam productListParam) {
        logger.info("Product list page requested");

        model.addAttribute("products", productService.findWithFilter(productListParam));

        return "products";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        logger.info("New product page requested");
        model.addAttribute("product", new Product());

        return "product_form";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        logger.info("Edit product page requested");
        model.addAttribute("product", productService.findById(id));

        return "product_form";
    }

    @PostMapping
    public String update(@Valid Product product,  BindingResult result) {
        logger.info("Saving product");

        if (result.hasErrors()) {
            return "product_form";
        }

        productService.save(product);

        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        logger.info("Deleting product");
        productService.deleteById(id);

        return "redirect:/product";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
