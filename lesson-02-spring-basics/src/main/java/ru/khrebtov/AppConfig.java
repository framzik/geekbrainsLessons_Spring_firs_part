package ru.khrebtov;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("ru.khrebtov")
public class AppConfig {

    @Bean
    @Scope("prototype")
    public Camera camera(CameraRoll cameraRoll) {
        return new Camera(cameraRoll);
    }
}
