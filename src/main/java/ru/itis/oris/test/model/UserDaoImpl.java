package ru.itis.oris.test.model;

import ru.itis.oris.test.util.DatabaseUtil;
import java.sql.*;
import java.util.*;

public abstract class UserDaoImpl implements UserDao {

    @Override
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setHashPassword(rs.getString("hash_password"));
                user.setRole(Role.valueOf(rs.getString("role")));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


}