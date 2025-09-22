package com.autoindustry.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "parts")
@Data
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private double price;

    private int stock;
}