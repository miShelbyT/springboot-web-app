package com.talbert.inventorymgmt.services;

import com.talbert.inventorymgmt.entities.User;
import com.talbert.inventorymgmt.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, BCryptPasswordEncoder bCryptPasswordEncoder1) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User getUserByUsername(String email) {
        return userRepository.findFirstByEmail(email);
    }

    public User addUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public  User updateUser(User user) {
        return userRepository.save(user);
    }

    public boolean authenticate(String email, String password) {
        User user = userRepository.findFirstByEmail(email);

        if(!user.getEmail().equals(email)){
            throw new UsernameNotFoundException("User does not exist in the database");
        }

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("The password is incorrect");
        }
        return  true;
    }


}
