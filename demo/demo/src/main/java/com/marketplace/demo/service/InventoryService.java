package com.marketplace.service;

import com.marketplace.entity.SparePart;
import com.marketplace.repository.SparePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private SparePartRepository repo;

    @Autowired
    private RedisTemplate<String, SparePart> redisTemplate;

    @Autowired
    private KafkaTemplate<String, SparePart> kafkaTemplate;

    public SparePart addPart(SparePart part) {
        SparePart saved = repo.save(part);
        redisTemplate.opsForValue().set("part:" + saved.getId(), saved);
        kafkaTemplate.send("inventory-topic", saved);
        return saved;
    }

    // Get from Redis cache
    public SparePart getPart(Long id) {
        return redisTemplate.opsForValue().get("part:" + id);
    }
}