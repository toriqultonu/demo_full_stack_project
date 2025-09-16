package com.marketplace.listener;

import com.marketplace.entity.SparePart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryListener {

    @Autowired
    private RedisTemplate<String, SparePart> redisTemplate;

    @KafkaListener(topics = "inventory-topic", groupId = "marketplace-group")
    public void listen(SparePart part) {
        redisTemplate.opsForValue().set("part:" + part.getId(), part);
        // Sync to Postgres if needed, but since add saves to Postgres first
    }
}