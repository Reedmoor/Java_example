package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Data
public class UserEntity {
    @Id
    Integer id;
    String name;
    String email;
    String password;
}