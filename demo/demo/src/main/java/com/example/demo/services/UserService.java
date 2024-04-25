package com.example.demo.services;

import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.UserEntity;
import com.example.demo.repository.UserRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    final UserRepo userRepo;

    public UserEntity registration(final UserEntity user) throws UserAlreadyExistException {
        val foundUser = userRepo.findByName(user.getName());
        if (foundUser != null) {
            throw new UserAlreadyExistException("Пользователь уже существует");
        }
        return userRepo.save(user);
    }

    public UserEntity getInfo(Integer id) throws UserNotFoundException {
        val userOpt = userRepo.findById(id);
        if (userOpt.isPresent()) {
            return userOpt.get();
        } else {
            throw new UserNotFoundException("Пользователь не найден");
        }
    }

    public Integer delete(Integer id) {
        userRepo.deleteById(id);
        return id;
    }
}
