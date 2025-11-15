package ru.itis.oris.test.dao;

import ru.itis.oris.test.entity.BookEntity;
import ru.itis.oris.test.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDao extends Dao {
    public BookEntity create(BookEntity entity) throws DatabaseException {
        String query = """
                insert into books (title, author, available)
                values (?, ?, ?);
                """;
        try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getAuthor());
            preparedStatement.setBoolean(3, entity.isAvailable());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }
            entity.setId(resultSet.getInt("id"));
            return entity;
        } catch (SQLException e) {
            throw new DatabaseException("Error while executing query: " + e.getMessage());
        }
    }

    public BookEntity get(BookEntity entity) throws DatabaseException {
        String query = "select title, author, available from users where id = ?;";
        try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
            preparedStatement.setInt(1, entity.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            entity.setTitle(resultSet.getString("title"));
            entity.setAuthor(resultSet.getString("author"));
            entity.setAvailable(resultSet.getBoolean("available"));
            return entity;
        } catch (SQLException e) {
            throw new DatabaseException("Error while executing query: " + e.getMessage());
        }
    }
}
