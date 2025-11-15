package ru.itis.oris.test.model;

public class UserEntity {
    private Long id;
    private String username;
    private String hashPassword;
    private String role;

    public UserEntity() {}

    public UserEntity(Long id, String username, String hashPassword, String role) {
        this.id = id;
        this.username = username;
        this.hashPassword = hashPassword;
        this.role = role;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
