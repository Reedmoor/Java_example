package com.example.demo.services;

import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.UserEntity;
import com.example.demo.repository.UserRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @NonNull
    private UserRepo userRepo;

    public UserEntity registration(final UserEntity user) throws UserAlreadyExistException {
        val foundUser = userRepo.findByName(user.getName());
        System.out.print(foundUser);
        if (foundUser != null) {
            throw new UserAlreadyExistException("User already exist");
        }
        return userRepo.save(user);
    }

    public UserEntity getInfo(Integer id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }

    public Integer delete(Integer id) {
        userRepo.deleteById(id);
        return id;
    }
}
