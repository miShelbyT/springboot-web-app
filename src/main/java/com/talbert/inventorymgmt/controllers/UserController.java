package com.talbert.inventorymgmt.controllers;

import com.talbert.inventorymgmt.entities.User;
import com.talbert.inventorymgmt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users_new")
    public ResponseEntity<User> newUser(@RequestBody() User user){
        User newUser = userService.getUserByUsername(user.getEmail());
        if(newUser == null){
            newUser = userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(newUser);
        }
    }

}
