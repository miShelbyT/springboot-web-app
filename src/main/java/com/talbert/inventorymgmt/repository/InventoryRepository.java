package com.talbert.inventorymgmt.repository;

import com.talbert.inventorymgmt.entities.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryItem, Integer> {
}
