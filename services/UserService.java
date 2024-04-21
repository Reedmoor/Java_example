package com.example.demo.services;

import com.example.demo.errors.UserAlreadyExistError;
import com.example.demo.errors.UserNotFoundError;
import com.example.demo.models.UserEntity;
import com.example.demo.repository.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public UserEntity registration(@RequestBody UserEntity user) {
        if (userRepo.findByName(user.getName()) != null) {
            throw new UserAlreadyExistError("User already exist");
        }
        return userRepo.save(user);
    }

    public UserEntity getInfo(Integer id) {
        UserEntity user = userRepo.findById(id).get();
        if (user == null){
           throw new UserNotFoundError("User not found");
        }
        return user;
    }

    public Integer delete(Integer id){
        userRepo.deleteById(id);
        return id;
    }
}
