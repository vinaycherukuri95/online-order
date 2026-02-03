package com.order.dto;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users",
uniqueConstraints = {
@UniqueConstraint(columnNames = "email")
    })
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String phonenumber;

    // getters and setters
}