package com.autoindustry.backend.repository;

import com.autoindustry.backend.document.RepairRequestDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRequestRepository extends MongoRepository<RepairRequestDocument, String> {
}