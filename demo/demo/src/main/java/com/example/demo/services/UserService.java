package com.example.demo.services;

import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.UserDto;
import com.example.demo.models.UserEntity;
import com.example.demo.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.io.Console;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    final UserRepo userRepo;

    public UserEntity registration(final UserDto userDto) throws UserAlreadyExistException {
        val foundUser = userRepo.findByName(userDto.getName());
        if (foundUser != null) {
            throw new UserAlreadyExistException("Пользователь уже существует");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());

        return userRepo.save(userEntity);
    }

    public UserEntity getInfo(final Integer id) throws UserNotFoundException {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
    }

    public String delete(final Integer id) throws UserNotFoundException {
        userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
        userRepo.deleteById(id);
        return "Пользователь успешно удален";
    }
}
