package com.autoindustry.backend.service;

import com.autoindustry.backend.document.RepairRequestDocument;
import com.autoindustry.backend.repository.RepairRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    @Autowired
    private RepairRequestRepository requestRepository;

    public RepairRequestDocument submitRequest(RepairRequestDocument request) {
        return requestRepository.save(request);
    }

    public List<RepairRequestDocument> getAllRequests() {
        return requestRepository.findAll();
    }

    public RepairRequestDocument getRequestById(String id) {
        return requestRepository.findById(id).orElse(null);
    }
}