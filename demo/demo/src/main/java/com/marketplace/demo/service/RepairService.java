package com.marketplace.service;

import com.marketplace.entity.RepairRequest;
import com.marketplace.entity.Quote;
import com.marketplace.repository.RepairRequestRepository;
import com.marketplace.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepairService {

    @Autowired
    private RepairRequestRepository requestRepo;

    @Autowired
    private QuoteRepository quoteRepo;

    public RepairRequest submitRequest(RepairRequest request) {
        return requestRepo.save(request);
    }

    public Quote submitQuote(Quote quote) {
        return quoteRepo.save(quote);
    }

    // Analytics: average quote
    public Double getAverageQuoteByBrand(String brand) {
        // Query Mongo for requests by brand, then avg quotes from Postgres
        // Implement logic
        return 0.0;
    }
}