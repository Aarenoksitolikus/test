package ru.itis.oris.test.dao;

import ru.itis.oris.test.model.User;
import ru.itis.oris.test.util.DatabaseUtil;
import ru.itis.oris.test.util.PasswordUtil;
import java.sql.*;
import java.util.Optional;

public class UserDao {

    public Optional<User> findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            User us = new User();
            us.setId(rs.getInt("id"));
            us.setUsername(rs.getString("username"));
            us.setHashPassword(rs.getString("hashPassword"));
            us.setRole(rs.getString("role"));
            return Optional.of(us);
        }
        return Optional.empty();
    }

    public void save(User us) throws SQLException {
        String sql = "INSERT INTO users (username, hashPassword, role) VALUES (?, ?, ?)";
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, us.getUsername());
        stmt.setString(2, PasswordUtil.hash(us.getHashPassword()));
        stmt.setString(3, "USER");
        stmt.executeUpdate();
    }

    public boolean checkPassword(String username, String password) throws SQLException {
        Optional<User> user = findByUsername(username);
        return user.isPresent() && PasswordUtil.check(password, user.get().getHashPassword());
    }
}