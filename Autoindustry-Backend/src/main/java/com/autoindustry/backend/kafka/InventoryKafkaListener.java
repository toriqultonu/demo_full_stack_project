package com.autoindustry.backend.kafka;

import com.autoindustry.backend.model.Part;
import com.autoindustry.backend.repository.PartRepository;
import com.autoindustry.backend.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryKafkaListener {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "inventory-updates", groupId = "inventory-group")
    public void listen(Part part) {
        partRepository.save(part);
        messagingTemplate.convertAndSend("/topic/inventory", partRepository.findAll());  // Broadcast update
    }
}