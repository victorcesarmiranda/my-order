package com.example.myorder.entities;

import io.swagger.models.auth.In;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ig;

    @ManyToOne
    @JoinColumn(name = "USER_ORDER_ID", nullable = false)
    private Order order;

    @Column( name = "QUANTITY" , nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "PRODUCT", nullable = false)
    private Product product;

    public Integer getIg() {
        return ig;
    }

    public OrderItem setIg(Integer ig) {
        this.ig = ig;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public OrderItem setOrder(Order order) {
        this.order = order;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderItem setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public OrderItem setProduct(Product product) {
        this.product = product;
        return this;
    }
}
