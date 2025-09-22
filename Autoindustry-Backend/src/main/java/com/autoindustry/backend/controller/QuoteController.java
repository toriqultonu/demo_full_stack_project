package com.autoindustry.backend.controller;

import com.autoindustry.backend.model.Quote;
import com.autoindustry.backend.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @PostMapping
    @PreAuthorize("hasRole('REPAIR_SHOP')")
    public ResponseEntity<Quote> submitQuote(@RequestBody Quote quote) {
        return ResponseEntity.ok(quoteService.submitQuote(quote));
    }

    @GetMapping("/{requestId}")
    @PreAuthorize("hasRole('CAR_OWNER')")
    public ResponseEntity<List<Quote>> getQuotes(@PathVariable String requestId) {
        return ResponseEntity.ok(quoteService.getQuotesByRequestId(requestId));
    }

    @GetMapping("/analytics/avg/{carBrand}")
    public ResponseEntity<Double> getAveragePrice(@PathVariable String carBrand) {
        return ResponseEntity.ok(quoteService.getAveragePriceByCarBrand(carBrand));
    }
}