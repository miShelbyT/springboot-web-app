package com.talbert.inventorymgmt.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class LoginRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    private boolean admin;


    public LoginRequest(Integer id, String email, String password, boolean admin) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    public LoginRequest() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId(){
       return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.admin = isAdmin;
    }

    public boolean getIsAdmin() {
        return admin;
    }


}
