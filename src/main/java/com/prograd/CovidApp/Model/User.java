package com.prograd.CovidApp.Model;

import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.time.Period;
//import javax.persistence.Table;

import javax.annotation.processing.Generated;

@Document(collection="Users")
public class User {
    @Id
    private int id;
    private String fullName;
    private String dob;
    private String username;
    private String email;
    private String password;
    private boolean admin = false;

    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
        LocalDate db = LocalDate.parse(dob);
        LocalDate curr = LocalDate.now();
        this.age = Period.between(db, curr).getYears();
    }

    public User() {}

    public User(int id, String fullName, String dob, String username, String email, String password) {
        this.id = id;
        if(fullName!=null)
            this.fullName = fullName;
        if(username!=null)
            this.username = username;
        if(email!=null)
            this.email = email;
        if(password!=null)
            this.password = password;
        this.admin = false;
        if(dob!=null) {
            this.dob = dob;
            LocalDate curr = LocalDate.now();
            LocalDate db = LocalDate.parse(dob);
            this.age = Period.between(db, curr).getYears();
        }
    }

    public String getUsername() {
        return username;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin() {
        this.admin = true;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getAge() {
        return age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", dob='" + dob + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
