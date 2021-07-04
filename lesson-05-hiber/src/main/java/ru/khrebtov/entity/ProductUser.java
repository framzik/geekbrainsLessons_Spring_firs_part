package ru.khrebtov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products_users")
public class ProductUser {

    @Id
    @Column(name = "users_id")
    public Long usersId;

    @Column(name = "products_id")
    public Long productsId;

    public ProductUser() {
    }

    public Long getUsersId() {
        return usersId;
    }

    public void setUsersId(Long usersId) {
        this.usersId = usersId;
    }

    public Long getProductsId() {
        return productsId;
    }

    public void setProductsId(Long productsId) {
        this.productsId = productsId;
    }
}
