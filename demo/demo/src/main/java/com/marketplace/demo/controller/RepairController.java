package com.marketplace.controller;

import com.marketplace.entity.RepairRequest;
import com.marketplace.entity.Quote;
import com.marketplace.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class RepairController {

    @Autowired
    private RepairService service;

    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping("/owner/request")
    public RepairRequest submitRequest(@RequestBody RepairRequest request) {
        return service.submitRequest(request);
    }

    @PostMapping("/shop/quote")
    public Quote submitQuote(@RequestBody Quote quote) {
        Quote saved = service.submitQuote(quote);
        template.convertAndSend("/topic/quotes/" + quote.getRequestId(), saved); // Real-time to owner
        return saved;
    }
}