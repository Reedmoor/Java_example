package com.example.demo.controllers;

import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.UserDto;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    final UserService userService;

    @PostMapping ("/reg")
    public ResponseEntity registration(@RequestBody UserDto userDto) {
        try {
            userService.registration(userDto);
            return ResponseEntity.ok("Пользователь сохранен");
        } catch (UserAlreadyExistException thrown) {
            log.error("Ошибка регистрации: {}", thrown.getMessage(), thrown);
            return ResponseEntity.badRequest().body(thrown.getMessage());
        } catch (Exception thrown) {
            return ResponseEntity.badRequest().body("Ошибка регистрации");
        }
    }

    @GetMapping("/info") // пример запроса http://localhost:8080/users/info?id=53
    public ResponseEntity getUserInfo(@RequestParam int id) {
        try {
            return ResponseEntity.ok(userService.getInfo(id));
        } catch (UserNotFoundException thrown) {
            log.error("Пользователь не найден: {}", thrown.getMessage(), thrown);
            return ResponseEntity.badRequest().body(thrown.getMessage());
        } catch (Exception thrown) {
            return ResponseEntity.badRequest().body("Ошибка при запросе информации о пользователе");
        }
    }

    @DeleteMapping ("/delete") // пример запроса http://localhost:8080/users/delete?id=53
    public ResponseEntity deleteUser(@RequestParam int id) {
        try {
            return ResponseEntity.ok(userService.delete(id));
        } catch (Exception thrown) {
            log.error("Ошибка при удалении пользователя: {}", thrown.getMessage(), thrown);
            return ResponseEntity.badRequest().body(thrown.getMessage());
        }
    }
}

