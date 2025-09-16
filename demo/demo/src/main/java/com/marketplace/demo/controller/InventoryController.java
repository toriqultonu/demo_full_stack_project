package com.marketplace.controller;

import com.marketplace.entity.SparePart;
import com.marketplace.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendor/inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping
    public SparePart addPart(@RequestBody SparePart part) {
        SparePart saved = service.addPart(part);
        template.convertAndSend("/topic/inventory", saved); // Real-time update
        return saved;
    }

    @GetMapping("/{id}")
    public SparePart getPart(@PathVariable Long id) {
        return service.getPart(id);
    }
}