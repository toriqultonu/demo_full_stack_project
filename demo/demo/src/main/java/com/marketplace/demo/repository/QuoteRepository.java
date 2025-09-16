package com.marketplace.repository;

import com.marketplace.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    @Query("SELECT AVG(q.totalPrice) FROM Quote q JOIN RepairRequest r ON q.requestId = r.id WHERE r.carBrand = ?1")
    Double averageQuoteByBrand(String brand); // Cross DB query - implement service level
}