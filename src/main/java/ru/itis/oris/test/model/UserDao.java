package ru.itis.oris.test.model;

import ru.itis.oris.test.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {

    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(User.builder()
                        .id(rs.getInt("id"))
                        .username(rs.getString("username"))
                        .hashPassword(rs.getString("hashPassword"))
                        .role(rs.getString("role"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void save(User user) {
        String sql = "INSERT INTO users (username, hashPassword, role) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getHashPassword());
            stmt.setString(3, user.getRole());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(User.builder()
                        .id(rs.getInt("id"))
                        .username(rs.getString("username"))
                        .hashPassword(rs.getString("hashPassword"))
                        .role(rs.getString("role"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}