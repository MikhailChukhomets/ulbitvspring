package com.example.ulbitvspring.controller;

import com.example.ulbitvspring.entity.UserEntity;
import com.example.ulbitvspring.exception.UserAlreadyExist;
import com.example.ulbitvspring.exception.UserNotFoundException;
import com.example.ulbitvspring.repository.UserRepo;
import com.example.ulbitvspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;

@RestController
@RequestMapping("/users")
public class UserController{

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok("User is save");
        } catch (UserAlreadyExist e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }


    @GetMapping ("/")
    public ResponseEntity getOneUser (@RequestParam Long id) {
        try{
            return ResponseEntity.ok(userService.getOne(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(userService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

}