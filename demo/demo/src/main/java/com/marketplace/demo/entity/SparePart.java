// PostgreSQL for reporting, Redis for cache
package com.marketplace.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class SparePart {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String category;

    private Double price;

    private Integer stock;
}