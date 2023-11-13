package ru.ageev.SpringSecurity.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.sql.Date;

@Entity
@Table(name = "people")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "username")
    @NotEmpty(message = "username can't be empty")
    @Size(min = 2, max = 100, message = "username length must be between 2 and 100 characters")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "password can't be empty")
    @Size(min = 2, max = 100, message = "password length must be between 2 and 100 characters")
    private String password;

    @Column(name = "date")
    private Date date;

    @Column(name = "role")
    private String role;

    public Person(String username, String password, Date date, String role) {
        this.username = username;
        this.password = password;
        this.date = date;
        this.role = role;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
