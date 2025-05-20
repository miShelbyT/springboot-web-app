package com.talbert.inventorymgmt.entities;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name="inventory_items")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String orderDate = LocalDate.now().toString();
    private String name;
    private String type;
    private String size;
    private String color;
    private String vendor;
    private int quantity;
    private int numSold = 0;
    private int numAvailable;
    private float purchasePrice;
    private float salesPrice;
//    saleStatus can be full price or on sale
    private String saleStatus = "full price";
    private int lastUpdatedBy;
    private String lastUpdatedDate = LocalDate.now().toString();;


    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager;

    public InventoryItem(int id, String orderDate, String name, String type, String size, String color, String vendor, int quantity, int purchasePrice, float salesPrice, String saleStatus, int lastUpdatedBy, String lastUpdatedDate, Manager manager) {
        this.id = id;
        this.orderDate = orderDate;
        this.name = name;
        this.type = type;
        this.size = size;
        this.color = color;
        this.vendor = vendor;
        this.quantity = quantity;
        this.numSold = 0;
        this.numAvailable = quantity;
        this.purchasePrice = purchasePrice;
        this.salesPrice = salesPrice;
        this.saleStatus = saleStatus;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedDate = lastUpdatedDate;
        this.manager = manager;
    }

    public InventoryItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getNumSold() {
        return numSold;
    }

    public void setNumSold(int numSold) {
        this.numSold = numSold;
    }

    public int getNumAvailable() {
        return numAvailable;
    }

    public void setNumAvailable(int numAvailable) {
        this.numAvailable = numAvailable;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public float getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(float salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public int getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(int lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
