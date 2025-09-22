package com.autoindustry.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.jasypt.hibernate5.encryptor.HibernatePBEStringEncryptor;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    @Type(type = "org.jasypt.hibernate5.type.EncryptedStringType")
    @org.hibernate.annotations.Parameter(name = "encryptor_registered_name", value = "jasyptStringEncryptor")
    private String email;  // Encrypted field

    // For searchable encryption, use deterministic encryption or hash prefix
}