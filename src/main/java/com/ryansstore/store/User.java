package com.ryansstore.store;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;

    public User(long id, String email, String password, String name, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // OLD: using automatic getter/setter methods from Lombok
    /*public void setId(long id) { this.id = id; }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public void setName(String name) { this.name = name; }

    public long getId() { return id; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public String getName() { return name; }*/
}
