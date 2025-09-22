package com.autoindustry.backend.controller;

import com.autoindustry.backend.document.RepairRequestDocument;
import com.autoindustry.backend.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping
    @PreAuthorize("hasRole('CAR_OWNER')")
    public ResponseEntity<RepairRequestDocument> submitRequest(@RequestBody RepairRequestDocument request) {
        return ResponseEntity.ok(requestService.submitRequest(request));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('REPAIR_SHOP', 'VENDOR')")
    public ResponseEntity<List<RepairRequestDocument>> getAllRequests() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }
}