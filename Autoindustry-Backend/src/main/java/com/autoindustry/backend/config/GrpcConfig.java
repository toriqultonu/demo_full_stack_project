package com.autoindustry.backend.config;

import com.autoindustry.backend.grpc.InventoryServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    @Value("${grpc.client.inventory-service.address}")
    private String inventoryServiceAddress;

    @Bean
    public ManagedChannel inventoryChannel() {
        return ManagedChannelBuilder.forTarget(inventoryServiceAddress)
                .usePlaintext()
                .build();
    }

    @Bean
    public InventoryServiceGrpc.InventoryServiceBlockingStub inventoryStub(ManagedChannel inventoryChannel) {
        return InventoryServiceGrpc.newBlockingStub(inventoryChannel);
    }
}