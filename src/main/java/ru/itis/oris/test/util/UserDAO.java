package ru.itis.oris.test.util;

import ru.itis.oris.test.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
