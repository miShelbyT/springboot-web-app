package com.talbert.inventorymgmt.controllers;

import com.talbert.inventorymgmt.entities.LoginRequest;
import com.talbert.inventorymgmt.entities.Manager;
import com.talbert.inventorymgmt.entities.User;
import com.talbert.inventorymgmt.services.ManagerService;
import com.talbert.inventorymgmt.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final ManagerService managerService;
    private final UserService userService;


    public LoginController(ManagerService managerService, UserService userService) {
        this.managerService = managerService;
        this.userService = userService;
    }

    @PostMapping("/login_users")
    public ResponseEntity<LoginRequest> login(@RequestBody LoginRequest loginRequest) {
        try {
            boolean isAuthenticated = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            return checkAuthenticated(isAuthenticated, userService, loginRequest);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(loginRequest);
        }
    }

    @PostMapping("/login_managers")
    public ResponseEntity<LoginRequest> loginMgrs(@RequestBody LoginRequest loginRequest) {
        try {
            boolean isAuthenticated = managerService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            return checkAuthenticated(isAuthenticated, managerService, loginRequest);
        } catch(Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginRequest);
        }
    }

    public ResponseEntity<LoginRequest> checkAuthenticated(boolean check, Object service, LoginRequest loginRequest) {
        if (check) {
            if(service instanceof ManagerService managerService) {
                Manager mgr = managerService.getMgrByUsername(loginRequest.getEmail());
                loginRequest.setId(mgr.getId());
                loginRequest.setEmail(mgr.getEmail());
                loginRequest.setIsAdmin(mgr.isAdmin());
            } else if (service instanceof UserService userService) {
                User user = userService.getUserByUsername(loginRequest.getEmail());
                loginRequest.setId(user.getId());
                loginRequest.setEmail(user.getEmail());
                loginRequest.setIsAdmin(user.isAdmin());
            }
            loginRequest.setPassword("");
            return ResponseEntity.status(HttpStatus.OK).body(loginRequest);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginRequest);
        }
    }

}


