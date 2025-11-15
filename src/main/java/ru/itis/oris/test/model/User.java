package ru.itis.oris.test.model;

public class User {
    private Integer id;
    private String username;
    private String hashPassword;
    private String role;

    public User() {}

    public User(Integer id, String username, String hashPassword, String role) {
        this.id = id;
        this.username = username;
        this.hashPassword = hashPassword;
        this.role = role;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getHashPassword() { return hashPassword; }
    public void setHashPassword(String hashPassword) { this.hashPassword = hashPassword; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}