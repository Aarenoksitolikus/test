package ru.itis.oris.test.util;

import ru.itis.oris.test.model.Role;
import ru.itis.oris.test.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void createUser(String username, String pass) throws SQLException {
        String sql = "insert into users (username, hashpassword, role) values (?, ?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, pass);
            ps.setString(3, Role.USER.name());
            ps.executeUpdate();
        }
    }

    public boolean isUserExist(String username) throws SQLException {
        String sql = "select 1 from users where username = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            try(ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public User getUserByusername(String username) throws SQLException {
        String sql = "select * from users where username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getLong("id"),
                            rs.getString("username"),
                            rs.getString("hashpassword"),
                            Role.valueOf(rs.getString("role"))
                    );
                }
            }
        }
        return null;
    }
}
