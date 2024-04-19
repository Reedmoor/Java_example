package com.example.demo.services;

import com.example.demo.exception.UserAlreadyExistError;
import com.example.demo.models.UserEntity;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public ResponseEntity registration(@RequestBody UserEntity user) throws UserAlreadyExistError {
        if (userRepo.findByName(user.getName()) != null) {
            throw new UserAlreadyExistError("пользователь существует");
        }
        userRepo.save(user);
        return ResponseEntity.ok("Сохранение успешно");
    }
}
