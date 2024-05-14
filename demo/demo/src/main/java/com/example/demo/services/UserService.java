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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    final UserRepo userRepo;

    public UserEntity registration(final UserDto userDto) throws UserAlreadyExistException {
        val foundUser = Optional.ofNullable(userRepo.findByName(userDto.getName()))
                .orElseThrow(() -> new UserAlreadyExistException("Пользователь уже существует"));

        val userEntity = UserEntity.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .build();

        return userRepo.save(userEntity);
    }

    public UserEntity getInfo(final Integer id) throws UserNotFoundException {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
    }

    public String delete(final Integer id) throws UserNotFoundException {
        return userRepo.findById(id)
                .map(user -> {
                    userRepo.deleteById(id);
                    return "Пользователь успешно удален";
                })
                .orElseThrow(() -> new UserNotFoundException("Пользователь с ID " + id + " не найден"));
    }
}
