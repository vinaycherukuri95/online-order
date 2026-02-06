package com.order.order;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foodName;
    private int quantity;
    private double price;
}