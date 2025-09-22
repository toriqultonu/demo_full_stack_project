package com.autoindustry.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableCaching
@EnableKafka
public class AutoindustryBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoindustryBackendApplication.class, args);
    }
}
