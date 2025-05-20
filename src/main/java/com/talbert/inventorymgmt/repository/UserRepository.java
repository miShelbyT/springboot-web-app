package com.talbert.inventorymgmt.repository;

import com.talbert.inventorymgmt.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findFirstByEmail(String email);
}
