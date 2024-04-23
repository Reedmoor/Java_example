package com.example.demo.repository;

import com.example.demo.models.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Integer> {
    UserEntity findByName(String name);
}
