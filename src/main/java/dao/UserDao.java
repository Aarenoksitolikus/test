package dao;

import model.User;
import model.Role;
import util.DatabaseConnection;

import java.sql.*;
import java.util.Optional;

public class UserDao {
    
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setHashPassword(resultSet.getString("hash_password"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    public void save(User user) {
        String sql = "INSERT INTO users (username, hash_password, role) VALUES (?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getHashPassword());
            statement.setString(3, user.getRole().name());
            statement.executeUpdate();
            
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public User findById(Integer id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setHashPassword(resultSet.getString("hash_password"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}