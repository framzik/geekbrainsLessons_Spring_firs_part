package ru.khrebtov;

import ru.khrebtov.persist.Product;
import ru.khrebtov.persist.ProductDao;

public class Main {

    public static void main(String[] args) {
        ProductDao dao = new ProductDao();

        dao.saveOrUpdate(new Product("car", 10d));
        dao.saveOrUpdate(new Product("car", 20d));
        dao.saveOrUpdate(new Product("car", 30d));

        System.out.println(dao.findAll());
        System.out.println(dao.findById(3L));
        dao.deleteById(3L);
        System.out.println(dao.findAll());
        dao.saveOrUpdate(new Product(2L, "moto", 600d));
        dao.saveOrUpdate(new Product("moto", 600d));
        System.out.println(dao.findAll());
    }
}
