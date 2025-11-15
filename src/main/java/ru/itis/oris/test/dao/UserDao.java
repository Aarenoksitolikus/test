package ru.itis.oris.test.dao;

import ru.itis.oris.test.entities.User;

import java.sql.*;
import java.util.List;

public class UserDao {

    private final Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void create(User user) throws SQLException {
        String sql = "INSERT INTO users (username, hash_password, role) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getHashPassword());
            stmt.setString(3, user.getRole());
            stmt.executeUpdate();
        }
    }

    // READ by id
    public User findById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setHashPassword(rs.getString("hash_password"));
                user.setRole(rs.getString("role"));
                return user;
            }
        }
        return null;
    }

    public String findUsernameById(int id) throws SQLException {
        String query = "SELECT username FROM users WHERE id = ?";

        //

        return null;
    }
}

