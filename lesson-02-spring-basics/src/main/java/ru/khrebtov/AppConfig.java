package ru.khrebtov;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.khrebtov.hw2.persist.ProductRepository;
import ru.khrebtov.hw2.persist.Cart;

@Configuration
@ComponentScan("ru.khrebtov")
public class AppConfig {

    @Bean
    @Scope("prototype")
    public Camera camera(CameraRoll cameraRoll) {
        return new Camera(cameraRoll);
    }

    @Bean
    @Scope("prototype")
    public Cart cart(ProductRepository repository) {
        return new Cart(repository);
    }
}
