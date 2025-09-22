package com.autoindustry.backend.controller;

import com.autoindustry.backend.model.Part;
import com.autoindustry.backend.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<Part> addPart(@RequestBody Part part) {
        return ResponseEntity.ok(inventoryService.addPart(part));
    }

    @GetMapping
    public ResponseEntity<List<Part>> getAllParts() {
        return ResponseEntity.ok(inventoryService.getAllParts());
    }
}