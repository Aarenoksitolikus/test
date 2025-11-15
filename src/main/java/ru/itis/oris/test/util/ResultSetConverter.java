package ru.itis.oris.test.util;

import ru.itis.oris.test.model.Book;
import ru.itis.oris.test.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetConverter {

    public static User convertToUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("hashpassword"),
                rs.getString("role")
        );
    }

    public static Book convertToBook(ResultSet rs) throws SQLException {
        return new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getBoolean("available")
        );
    }

}
