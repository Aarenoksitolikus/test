package ru.itis.oris.test.model;

public class User {
    private Long id;
    private String username;
    private String hashPassword;
    private String role;

    public User() {}

    public User(Long id, String username, String hashPassword, String role) {
        this.id = id;
        this.username = username;
        this.hashPassword = hashPassword;
        this.role = role;
    }


}