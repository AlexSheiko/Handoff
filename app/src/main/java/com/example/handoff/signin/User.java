package com.example.handoff.signin;

public class User {

    private String name;
    private String email;
    private String password;
    private String password_confirmation;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
        this.password_confirmation = password;
    }
}
