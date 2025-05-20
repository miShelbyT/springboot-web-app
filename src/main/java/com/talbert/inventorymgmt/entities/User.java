package com.talbert.inventorymgmt.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @NotEmpty(message = "Name may not be empty")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    private String name;

    private String title = "Sales Associate";

    private String startDate;

    @NotEmpty(message = "Email may not be empty")
    @Size(min = 2, max = 32, message = "Email must be between 8 and 20 characters long")
    private String email;
    private String password;
    private String passwordHash;
    private boolean isActive = false;

    public User() {}

    public User(int id, String name, String title, String startDate, String email, String password, String passwordHash, boolean isActive) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.startDate = startDate;
        this.email = email;
        this.password = password;
        this.passwordHash = passwordHash;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        this.isActive = active;
    }

    public boolean isAdmin() {
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", startDate='" + startDate + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
