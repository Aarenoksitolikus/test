package ru.itis.oris.test.model.entities;

public class User {
    private Long id;
    private String username;
    private String hashPassword;
    private String role; // "USER" or "MODERATOR"

    public User() {}
    public User(Long id, String username, String hashPassword, String role) {
        this.id = id; this.username = username; this.hashPassword = hashPassword; this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public String getRole() {
        return role;
    }
}
