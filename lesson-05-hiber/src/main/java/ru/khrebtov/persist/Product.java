package ru.khrebtov.persist;

import javax.persistence.*;

@Entity
@Table(name = "product")
@NamedQueries(
        {
                @NamedQuery(name = "getAllProducts", query = "select p from Product p")
        }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private double cost;

    public Product() {
    }

    public Product(String title, double cost) {
        this.title = title;
        this.cost = cost;
    }

    public Product(Long id, String title, double cost) {
        this(title, cost);

        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}';
    }
}
