package ru.itis.oris.test.model.entity;

public class UserEntity {
    private long id;
    private String username;
    private String hashPassword;
    private String role;

    public UserEntity(long id, String username, String hashPassword, String role){
        this.id = id;
        this.username = username;
        this.hashPassword = hashPassword;
        this.role = role;
    }

    public long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getHashPassword(){
        return this.hashPassword;
    }

    public String getRole() {
        return role;
    }
}
