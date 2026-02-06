package com.order.order;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private double total;
    private String status;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;
}