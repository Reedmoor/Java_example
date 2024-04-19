package com.example.demo.controllers;

import com.example.demo.exception.UserAlreadyExistError;
import com.example.demo.models.UserEntity;
import com.example.demo.repository.UserRepo;
import com.example.demo.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping ("/users")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping
    @RequestMapping("/reg")
    public ResponseEntity registration(@RequestBody UserEntity user){
        try{
            userService.registration(user);
            return ResponseEntity.ok("user saved");

        }catch(UserAlreadyExistError e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return  ResponseEntity.badRequest().body("error");
        }
    }


}
