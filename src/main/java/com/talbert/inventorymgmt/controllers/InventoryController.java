package com.talbert.inventorymgmt.controllers;

import com.talbert.inventorymgmt.entities.InventoryItem;
import com.talbert.inventorymgmt.entities.Manager;
import com.talbert.inventorymgmt.repository.InventoryRepository;
import com.talbert.inventorymgmt.repository.ManagerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/inventory_items")
public class InventoryController {

    private final InventoryRepository inventoryRepository;
    private final ManagerRepository managerRepository;


    public InventoryController(InventoryRepository inventoryRepository, ManagerRepository managerRepository) {
        this.inventoryRepository = inventoryRepository;
        this.managerRepository = managerRepository;
    }

    @GetMapping
    public List<InventoryItem> getAllInventoryItems(){
        return inventoryRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<InventoryItem> createInventoryItem(@RequestBody InventoryItem inventoryItem) {

        Optional<Manager> mgr = managerRepository.findById(inventoryItem.getManager().getId());
        if(mgr.isPresent()) {
            inventoryItem.setManager(mgr.get());
            InventoryItem savedItem = inventoryRepository.save(inventoryItem);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //automatically returns 200-OK
    @PutMapping("/{id}")
    public InventoryItem updateInventoryItem(@PathVariable Integer id, @RequestBody InventoryItem inventoryItem) {
        return inventoryRepository.findById(id)
                .map(item -> {
                    item.setOrderDate(inventoryItem.getOrderDate());
                    item.setName(inventoryItem.getName());
                    item.setType(inventoryItem.getType());
                    item.setSize(inventoryItem.getSize());
                    item.setColor(inventoryItem.getColor());
                    item.setVendor(inventoryItem.getVendor());
                    item.setQuantity(inventoryItem.getQuantity());
                    item.setNumSold(inventoryItem.getNumSold());
                    item.setNumAvailable(inventoryItem.getNumAvailable());
                    item.setPurchasePrice(inventoryItem.getPurchasePrice());
                    item.setSalesPrice(inventoryItem.getSalesPrice());
                    item.setSaleStatus(inventoryItem.getSaleStatus());
                    item.setLastUpdatedBy(inventoryItem.getLastUpdatedBy());
                    item.setLastUpdatedDate(inventoryItem.getLastUpdatedDate());
                    return inventoryRepository.save(item);
                })
        .orElseGet(()-> inventoryRepository.save(inventoryItem));
    }

    @DeleteMapping(value="/{id}", produces="application/json")
    ResponseEntity<Void> deleteInventoryItem(@PathVariable Integer id) {
        if(inventoryRepository.existsById(id)) {
            inventoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
