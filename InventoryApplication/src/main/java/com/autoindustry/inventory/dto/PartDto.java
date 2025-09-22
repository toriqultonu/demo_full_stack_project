package com.autoindustry.backend.dto;

import lombok.Data;

@Data
public class PartDto {
    private Long id;
    private String name;
    private String category;
    private double price;
    private int stock;
}