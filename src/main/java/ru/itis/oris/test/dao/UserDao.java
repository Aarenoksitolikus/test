package ru.itis.oris.test.dao;

import ru.itis.oris.test.config.ConnectionManager;
import ru.itis.oris.test.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao {
    private static final String INSERT_USER = """
        INSERT INTO users (username, password_hash, role)
        VALUES (?, ?, ?)
        RETURNING id;
    """;

    private static final String FIND_BY_EMAIL = """
        SELECT id, username, password_hash, role
        FROM users
        WHERE email = ?
        """;

    public Optional<User> findByUsername(String username) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_EMAIL)){

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password_hash"),
                    rs.getString("role")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public int save(User user) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_USER)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ps.setString(3, user.getRole());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранение", e);
        }
        return -1;
    }
}
