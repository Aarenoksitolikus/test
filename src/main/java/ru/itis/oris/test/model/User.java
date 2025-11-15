package ru.itis.oris.test.model;

public class User {
    private Long id;
    private String username;
    private String hashPassword;
    private Role role;

    public User() {
        this.id = id;
        this.hashPassword = hashPassword;
        this.username = username;
        this.role = role;
    }

    public void setId(long id) {
        id = this.id;
    }

    public void setRole(Role role) {
        role = this.role;

    }

    public void setUsername(String username) {
        username = this.username;
    }

    public void setHashPassword(String hashPassword) {
        hashPassword = this.hashPassword;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public Object getRole() {
        return role;
    }
}
