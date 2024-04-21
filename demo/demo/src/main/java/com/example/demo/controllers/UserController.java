package com.example.demo.controllers;

import com.example.demo.errors.UserAlreadyExistError;
import com.example.demo.errors.UserNotFoundError;
import com.example.demo.models.UserEntity;
import com.example.demo.services.UserService;
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
            return  ResponseEntity.badRequest().body("error reg");
        }
    }

    @GetMapping
    @RequestMapping("/info")
    //http://localhost:8080/users/info?id=53
    public ResponseEntity getUserInfo(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(userService.getInfo(id));

        }catch(UserNotFoundError e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }catch(Exception e){
            return ResponseEntity.badRequest().body("error info");
        }
    }

    @DeleteMapping
    @RequestMapping("/delete")
    //http://localhost:8080/users/delete?id=53
    public  ResponseEntity deleteUser(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(userService.delete(id));

        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

