package com.autoindustry.backend.repository;

import com.autoindustry.backend.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findByRequestId(String requestId);

    @Query("SELECT AVG(q.totalPrice) FROM Quote q WHERE q.requestId IN (SELECT r.id FROM RepairRequestDocument r WHERE r.carBrand = ?1)")
    Double getAveragePriceByCarBrand(String carBrand);  // Cross-DB in service
}