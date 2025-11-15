package model;

public class User {
    private Integer id;
    private String username;
    private String hashPassword;
    private Role role;

    public User() {}

    public User(String username, String hashPassword, Role role) {
        this.username = username;
        this.hashPassword = hashPassword;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
