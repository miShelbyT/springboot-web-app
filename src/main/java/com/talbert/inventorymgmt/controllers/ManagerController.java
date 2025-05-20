package com.talbert.inventorymgmt.controllers;


import com.talbert.inventorymgmt.entities.Manager;
import com.talbert.inventorymgmt.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/managers")
    public ResponseEntity<List<Manager>> getManagers() {
        return ResponseEntity.ok(managerService.getManagers());
    }

    @PostMapping("/managers_new")
    public ResponseEntity<Manager> newUser(@RequestBody() Manager user){
        Manager newUser = managerService.getMgrByUsername(user.getEmail());
        if(newUser == null){
            newUser = managerService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(newUser);
        }
    }

}
