package ru.itis.oris.test.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import ru.itis.oris.test.model.User;
import ru.itis.oris.test.util.HashUtil;
import ru.itis.oris.test.util.UserDAO;

import java.io.IOException;
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

    public boolean saveNewUser(String username, String password) throws SQLException {
        userDao.createUser(username, password);
        return true;
    }

    public boolean isUserExist(String username) throws SQLException {
        return userDao.isUserExist(username);
    }

    public User authenticateUser(String username, String pass) throws SQLException {
        User user = userDao.getUserByusername(username);

        if (HashUtil.verify(pass, user.getHashPassword())) {
            return user;
        }
        return null;
    }

    public static String checkUser(HttpServletRequest req) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

}
