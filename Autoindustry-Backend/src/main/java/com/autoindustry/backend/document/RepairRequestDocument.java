package com.autoindustry.backend.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "requests")
@Data
public class RepairRequestDocument {
    @Id
    private String id;

    private String ownerId;

    private String description;

    private String carBrand;

    private String carModel;

    private int year;

    private List<String> partsNeeded;
}