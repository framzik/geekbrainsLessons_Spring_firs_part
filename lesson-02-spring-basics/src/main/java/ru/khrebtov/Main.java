package ru.khrebtov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.khrebtov.hw2.persist.Cart;

public class Main {

    public static void main(String[] args) {
//        CameraRoll roll1 = new ColorCameraRoll();
//        CameraRoll roll2 = new BlackAndWhiteCameraRoll();
//        Camera camera = new Camera(roll2);
//        camera.doPhotography();

        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Camera camera1 = context.getBean("camera", Camera.class);
        Camera camera2 = context.getBean("camera", Camera.class);
        Camera camera3 = context.getBean("camera", Camera.class);

        camera1.doPhotography();

        BlockOfCameras blockOfCameras = context.getBean("blockOfCameras", BlockOfCameras.class);
        blockOfCameras.doPhotos();

        Cart cart1 = context.getBean("cart", Cart.class);
        cart1.addProductInCart(1L);
        cart1.removeProductInCart(1L);

        Cart cart2 = context.getBean("cart", Cart.class);
        cart2.addProductInCart(2L);
        cart2.removeProductInCart(2L);
        Cart cart3 = context.getBean("cart", Cart.class);
        cart2.addProductInCart(3L);
        cart2.removeProductInCart(4L);
    }
}
