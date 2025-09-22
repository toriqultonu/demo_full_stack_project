package com.autoindustry.backend.service;

import com.autoindustry.backend.grpc.InventoryServiceGrpc;
import com.autoindustry.backend.grpc.InventoryProto;
import com.autoindustry.backend.model.Part;
import com.autoindustry.backend.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryServiceGrpc.InventoryServiceBlockingStub inventoryStub;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private KafkaTemplate<String, Part> kafkaTemplate;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public Part addPart(Part part) {
        // Call gRPC microservice
        InventoryProto.AddPartRequest request = InventoryProto.AddPartRequest.newBuilder()
                .setName(part.getName())
                .setCategory(part.getCategory())
                .setPrice(part.getPrice())
                .setStock(part.getStock())
                .build();
        InventoryProto.PartResponse response = inventoryStub.addPart(request);
        Part newPart = new Part();
        newPart.setId(response.getId());
        newPart.setName(response.getName());
        newPart.setCategory(response.getCategory());
        newPart.setPrice(response.getPrice());
        newPart.setStock(response.getStock());
        kafkaTemplate.send("inventory-updates", newPart);  // Sync via Kafka
        return newPart;
    }

    @Cacheable(value = "parts", key = "#id")
    public Part getPartById(Long id) {
        return partRepository.findById(id).orElse(null);
    }

    @Cacheable(value = "parts")
    public List<Part> getAllParts() {
        return partRepository.findAll();
    }

    // Kafka listener in separate class
}