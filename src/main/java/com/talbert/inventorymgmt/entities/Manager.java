package com.talbert.inventorymgmt.entities;

import jakarta.persistence.*;

@Entity
@Table(name="managers")
public class Manager extends User{
    public Manager() {
        super();
        this.setTitle("Manager");
        this.setIsActive(true);
    }

    public boolean validateAccount(User user) {
        if(!user.getIsActive()) {
            user.setIsActive(true);
            return true;
        }
        return false;
    }

    public boolean deactivateAccount(User user) {
        if(user.getIsActive()) {
            user.setIsActive(false);
            return true;
        }
        return false;
    }

    //    method override for polymorphism and effective rendering on front end - only elements that manager can view
    @Override
    public boolean isAdmin() {
        return true;
    }
}
