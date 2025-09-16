package com.marketplace.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class QuoteItem {
    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private Double price;
}