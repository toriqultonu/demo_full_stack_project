package com.autoindustry.inventory.service;

import com.autoindustry.backend.grpc.InventoryProto;
import com.autoindustry.backend.grpc.InventoryServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl extends InventoryServiceGrpc.InventoryServiceImplBase {

    @Autowired
    private RedisTemplate<String, InventoryProto.PartResponse> redisTemplate;

    @Autowired
    private KafkaTemplate<String, InventoryProto.PartResponse> kafkaTemplate;

    @Override
    public void addPart(InventoryProto.AddPartRequest request, StreamObserver<InventoryProto.PartResponse> responseObserver) {
        // Simulate ID generation
        long id = System.currentTimeMillis();
        InventoryProto.PartResponse part = InventoryProto.PartResponse.newBuilder()
                .setId(id)
                .setName(request.getName())
                .setCategory(request.getCategory())
                .setPrice(request.getPrice())
                .setStock(request.getStock())
                .build();

        // Cache in Redis
        redisTemplate.opsForValue().set("part:" + id, part);

        // Publish to Kafka for sync
        kafkaTemplate.send("inventory-updates", part);

        responseObserver.onNext(part);
        responseObserver.onCompleted();
    }
}