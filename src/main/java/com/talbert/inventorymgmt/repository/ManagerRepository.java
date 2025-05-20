package com.talbert.inventorymgmt.repository;

import com.talbert.inventorymgmt.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    Manager findFirstByEmail(String email);
}
