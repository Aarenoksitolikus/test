package ru.itis.oris.test.repository;

import ru.itis.oris.test.model.User;
import ru.itis.oris.test.util.ConnectionManager;
import ru.itis.oris.test.util.ResultSetConverter;

import java.sql.*;

public class UserRepository {

    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return ResultSetConverter.convertToUser(rs);
        } catch (SQLException e) {
            throw  new RuntimeException(e.getMessage());
        }
    }

    public User save(User user) {
        String query = "INSERT INTO users (username, hashpassword, role) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getHashPassword());
            ps.setString(3, user.getRole());
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.next();
            Integer id = generatedKeys.getInt("id");
            user.setId(id);
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
