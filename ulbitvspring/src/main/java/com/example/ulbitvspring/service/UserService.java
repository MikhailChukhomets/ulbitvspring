package com.example.ulbitvspring.service;

import com.example.ulbitvspring.entity.UserEntity;
import com.example.ulbitvspring.exception.UserAlreadyExist;
import com.example.ulbitvspring.exception.UserNotFoundException;
import com.example.ulbitvspring.model.User;
import com.example.ulbitvspring.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration (UserEntity user) throws UserAlreadyExist {
        if (userRepo.findByUserName(user.getUserName()) != null) {
            throw  new UserAlreadyExist("User is exist");
        }
       return userRepo.save(user);
    }

    public User getOne (Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("User isn`t find");
        }
        return User.toModel(user);
    }
    public Long delete (Long id) {
        userRepo.deleteById(id);
        return id;
    }
}
