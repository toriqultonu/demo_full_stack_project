package com.marketplace.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.jasypt.hibernate5.encryptor.HibernatePBEStringEncryptor;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password; // Hashed

    @Enumerated(EnumType.STRING)
    private Role role;

    // Sensitive fields with encryption
    @Type(type = "encryptedString")
    private String email; // Example searchable encrypted field

    public enum Role {
        CAR_OWNER, REPAIR_SHOP_OWNER, VENDOR
    }
}