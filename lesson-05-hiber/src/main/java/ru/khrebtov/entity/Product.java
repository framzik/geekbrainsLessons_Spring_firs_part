package ru.khrebtov.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
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

    @ManyToMany
    @JoinTable(
            name = "products_users",
            joinColumns = @JoinColumn(name = "products_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id")
    )
    private List<User> users;

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

    public Product(Long id, String title, double cost, List<User> users) {
        this(id, title, cost);

        this.users = users;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
