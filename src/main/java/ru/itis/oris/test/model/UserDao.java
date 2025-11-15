package ru.itis.oris.test.model;

import java.sql.*;

public class UserDao {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/controlWork",
                "postgres", "Uekz2005");
    }

    private Statement getStatement() {
        try {
            return getConnection().createStatement();
        } catch (SQLException e) {
            System.out.println("ваще всё поломалось, Наташа, мы ваще всё уронили");
            throw new IllegalStateException(e);
        }
    }

    public UserEntity findByUsername(String username) throws SQLException {
        String sql = "select * from users where username = ?";
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UserEntity user = new UserEntity();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setHashPassword(rs.getString("hashPassword"));
                user.setRole(rs.getString("role"));
                return user;
            }
        }
        return null;
    }


    public void save(UserEntity user) throws SQLException {
        String sql = "insert into users (username, hashPassword, role) values (?, ?, ?)";

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getHashPassword());
            ps.setString(3, user.getRole());
            ps.executeUpdate();
        }
    }
}
