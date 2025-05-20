package com.talbert.inventorymgmt.security;

import com.talbert.inventorymgmt.entities.Manager;
import com.talbert.inventorymgmt.entities.User;
import com.talbert.inventorymgmt.repository.ManagerRepository;
import com.talbert.inventorymgmt.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;

    public MyUserDetailsService(UserRepository userRepository, ManagerRepository managerRepository) {
        this.userRepository = userRepository;
        this.managerRepository = managerRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findFirstByEmail(email);
        Manager manager = managerRepository.findFirstByEmail(email);

        if(user == null && manager == null) {
            throw new UsernameNotFoundException("User not found");
        } else if (user == null) {
            return new ManagerPrincipal(manager);
        } else return new UserPrincipal(user);
    }

}
