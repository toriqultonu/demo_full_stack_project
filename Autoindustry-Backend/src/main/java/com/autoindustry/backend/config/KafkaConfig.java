package com.autoindustry.backend.config;

import com.autoindustry.backend.dto.PartDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic inventoryUpdatesTopic() {
        return TopicBuilder.name("inventory-updates")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Custom serializers if needed, but using defaults from application.yml
}