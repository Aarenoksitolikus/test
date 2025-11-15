package ru.itis.oris.test.model;


public class User {
    long id;
    String userName;
    String hashPassword;
    Role role;

    public Role getRole() {
        return role;
    }

    public String getUserName() {
        return userName;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public User(long id, String userName, String hashPassword, Role role) {
        this.id = id;
        this.userName = userName;
        this.hashPassword = hashPassword;
        this.role = role;
    }
}
