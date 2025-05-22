package com.talbert.inventorymgmt.controllers;

import com.talbert.inventorymgmt.entities.JwtUtil;
import com.talbert.inventorymgmt.entities.LoginRequest;
import com.talbert.inventorymgmt.entities.Manager;
import com.talbert.inventorymgmt.entities.User;
import com.talbert.inventorymgmt.services.ManagerService;
import com.talbert.inventorymgmt.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final ManagerService managerService;
    private final UserService userService;
    private final JwtUtil jwtUtil;


    public LoginController(ManagerService managerService, UserService userService, JwtUtil jwtUtil) {
        this.managerService = managerService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login_users")
    public ResponseEntity<LoginRequest> login(@RequestBody LoginRequest loginRequest) {
        try {
            UserDetails userDetails = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            User user = userService.getUserByUsername(userDetails.getUsername());
            loginRequest.setIsAdmin(user.isAdmin());
            loginRequest.setId(user.getId());
            loginRequest.setEmail(user.getEmail());
            return checkAuthenticated(userDetails, loginRequest);
        } catch (UsernameNotFoundException | BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginRequest);
        }
    }

    @PostMapping("/login_managers")
    public ResponseEntity<LoginRequest> loginMgrs(@RequestBody LoginRequest loginRequest) {
        try {
            UserDetails userDetails = managerService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            Manager mgr = managerService.getMgrByUsername(userDetails.getUsername());
            loginRequest.setIsAdmin(mgr.isAdmin());
            loginRequest.setId(mgr.getId());
            loginRequest.setEmail(mgr.getEmail());
            return checkAuthenticated(userDetails, loginRequest);
        } catch (UsernameNotFoundException | BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginRequest);
        }
    }

    public ResponseEntity<LoginRequest> checkAuthenticated(UserDetails userDetails, LoginRequest loginRequest) {
        String token = jwtUtil.generateToken(userDetails);
        loginRequest.setToken(token);
        loginRequest.setPassword("******"); // mask password
        return ResponseEntity.ok(loginRequest);
    }

}