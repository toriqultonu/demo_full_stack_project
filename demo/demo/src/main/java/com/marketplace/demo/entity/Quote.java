// PostgreSQL Entity
package com.marketplace.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quote {
    @Id
    @GeneratedValue
    private Long id;

    private Long requestId; // Reference to Mongo ID as Long or String

    private Long shopId;

    private Double totalPrice;

    private Integer estimatedHours;

    @OneToMany
    private List<QuoteItem> items; // Breakdown
}