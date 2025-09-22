package com.autoindustry.inventory;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class InventoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

    @Bean
    public Server grpcServer(InventoryServiceImpl inventoryService) throws IOException {
        Server server = ServerBuilder.forPort(9090)
                .addService(inventoryService)
                .build();
        server.start();
        return server;
    }
}