// MongoDB Document
package com.marketplace.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "repair_requests")
@Data
public class RepairRequest {
    @Id
    private String id;

    private String description;

    private String carBrand;

    private String carModel;

    private Long ownerId;

    private List<String> partsNeeded; // Nested array
}