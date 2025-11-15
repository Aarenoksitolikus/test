package ru.itis.oris.test.service;

import ru.itis.oris.test.util.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserService {
    private UserDAO userDao;

    public UserService() {
        this.userDao = createUserDAO();
    }

    public UserDAO createUserDAO() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/oris",
                    "postgres",
                    "010909"
            );
            return new UserDAO(connection);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
