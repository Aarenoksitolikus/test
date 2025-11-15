package ru.itis.oris.test.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataManager {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка: " + e);
        }

        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/maxim",
                "postgres", "2006");
    }
}
