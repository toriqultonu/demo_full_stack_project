package com.autoindustry.backend.service;

import com.autoindustry.backend.document.RepairRequestDocument;
import com.autoindustry.backend.model.Quote;
import com.autoindustry.backend.repository.QuoteRepository;
import com.autoindustry.backend.repository.RepairRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuoteService {
    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private RepairRequestRepository requestRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public Quote submitQuote(Quote quote) {
        Quote saved = quoteRepository.save(quote);
        messagingTemplate.convertAndSend("/topic/quotes", saved);  // Real-time update
        return saved;
    }

    public List<Quote> getQuotesByRequestId(String requestId) {
        return quoteRepository.findByRequestId(requestId);
    }

    public double getAveragePriceByCarBrand(String carBrand) {
        List<String> requestIds = requestRepository.findAll().stream()
                .filter(r -> r.getCarBrand().equals(carBrand))
                .map(RepairRequestDocument::getId)
                .collect(Collectors.toList());
        return quoteRepository.findAll().stream()
                .filter(q -> requestIds.contains(q.getRequestId()))
                .mapToDouble(Quote::getTotalPrice)
                .average()
                .orElse(0.0);
    }
}