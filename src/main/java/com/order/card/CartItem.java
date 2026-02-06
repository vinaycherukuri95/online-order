package com.order.card;
import com.order.food.Food;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Food food;

    private int quantity;
    private double price;
}