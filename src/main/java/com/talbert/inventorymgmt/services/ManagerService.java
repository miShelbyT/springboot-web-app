package com.talbert.inventorymgmt.services;

import com.talbert.inventorymgmt.entities.Manager;
import com.talbert.inventorymgmt.repository.ManagerRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ManagerService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ManagerRepository managerRepository;

    public ManagerService(BCryptPasswordEncoder bCryptPasswordEncoder, ManagerRepository managerRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.managerRepository = managerRepository;
    }

    public Manager getMgrByUsername(String email){
        return managerRepository.findFirstByEmail(email);
    }

    public List<Manager> getManagers(){
        return managerRepository.findAll();
    }


    public Manager addUser(Manager user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return managerRepository.save(user);
    }

    public  Manager updateUser(Manager user) {
        return managerRepository.save(user);
    }

    public boolean adminStatus(String email) {
        Manager manager = managerRepository.findFirstByEmail(email);
        return manager.isAdmin();
    }

    public boolean authenticate(String email, String password) {
        Manager manager = managerRepository.findFirstByEmail(email);
        if(manager == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (!bCryptPasswordEncoder.matches(password, manager.getPassword())) {
            throw  new BadCredentialsException("The password is incorrect");
        }
        return  true;
    }
}
