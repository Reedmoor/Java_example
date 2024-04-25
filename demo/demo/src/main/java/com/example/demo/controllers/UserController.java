package com.example.demo.controllers;

import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.UserEntity;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    @PostMapping
    @RequestMapping("/reg")
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok("Пользователь сохранен");
        } catch (UserAlreadyExistException thrown) {
            return ResponseEntity.badRequest().body(thrown.getMessage());
        } catch (Exception thrown) {
            return ResponseEntity.badRequest().body("Ошибка регистрации");
        }
    }

    @GetMapping
    @RequestMapping("/info") // пример запроса http://localhost:8080/users/info?id=53
    public ResponseEntity getUserInfo(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(userService.getInfo(id));
        } catch (UserNotFoundException thrown) {
            return ResponseEntity.badRequest().body(thrown.getMessage());
        } catch (Exception thrown) {
            return ResponseEntity.badRequest().body("Ошибка при запросе информации о пользователе");
        }
    }

    @DeleteMapping
    @RequestMapping("/delete") // пример запроса http://localhost:8080/users/delete?id=53
    public ResponseEntity deleteUser(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(userService.delete(id));
        } catch (Exception thrown) {
            return ResponseEntity.badRequest().body(thrown.getMessage());
        }
    }
}

