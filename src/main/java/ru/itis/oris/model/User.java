package ru.itis.oris.model;

public class User {
    private Long id;
    private String username;
    private String hashPassword;
    private Role role;

    public enum Role {
        USER, ADMIN
    }

    public User() {}

    public User(Long id, String username, String hashPassword, Role role) {
        this.id = id;
        this.username = username;
        this.hashPassword = hashPassword;
        this.role = role;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getHashPassword() { return hashPassword; }
    public void setHashPassword(String hashPassword) { this.hashPassword = hashPassword; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
