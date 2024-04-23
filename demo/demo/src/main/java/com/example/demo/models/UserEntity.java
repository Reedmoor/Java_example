package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@FieldDefaults(makeFinal = true)
public class UserEntity {
    @Id
    @GeneratedValue
    Integer id;
    String name;
    String email;
    String password;
}