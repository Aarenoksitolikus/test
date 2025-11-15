package ru.itis.oris.test.dao;

import ru.itis.oris.test.exception.DatabaseException;

import java.sql.*;

public class Dao {
    private Connection connection;

    public PreparedStatement getPreparedStatement(String query) throws DatabaseException {
        try {
            return getConnection().prepareStatement(query);
        } catch (SQLException | ClassNotFoundException e) {
            throw new DatabaseException("Unable to get prepared statement: " + e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            Class.forName("org.postgresql.Driver");
            String DATABASE_URL = "jdbc:postgresql://localhost:5432/kr_1";
            String DATABASE_USER = "postgres";
            String DATABASE_PASSWORD = "postgres";
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        }
        return connection;
    }
}
