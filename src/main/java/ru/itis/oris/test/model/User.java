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

    public String getRole() { return role; }


    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Integer id;
        private String username;
        private String hashPassword;
        private String role;

        public UserBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder hashPassword(String hashPassword) {
            this.hashPassword = hashPassword;
            return this;
        }

        public UserBuilder role(String role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(id, username, hashPassword, role);
        }
    }
}