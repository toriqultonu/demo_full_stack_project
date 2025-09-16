package com.marketplace.repository;

import com.marketplace.entity.RepairRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepairRequestRepository extends MongoRepository<RepairRequest, String> {
    // Custom queries for analytics
}