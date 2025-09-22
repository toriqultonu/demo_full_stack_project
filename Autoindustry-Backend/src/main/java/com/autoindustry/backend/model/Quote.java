package com.autoindustry.backend.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "quotes")
@Data
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "repair_shop_id")
    private User repairShop;

    @Column(name = "request_id")
    private String requestId;  // Reference to MongoDB ID

    @Column(columnDefinition = "jsonb")
    private JsonNode priceBreakdown;

    private double estimatedHours;

    @Column
    private double totalPrice;
}