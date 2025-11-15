package ru.itis.oris.test.dao;

import ru.itis.oris.test.entity.UserEntity;
import ru.itis.oris.test.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends Dao {
    public UserEntity create(UserEntity entity) throws DatabaseException {
        String query = """
                insert into users (username, hashpassword, role)
                values (?, ?, ?)
                on conflict (username) do nothing
                returning id;
                """;
        try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getHashPassword());
            preparedStatement.setString(3, entity.getRole());
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

    public UserEntity getUser(UserEntity entity) throws DatabaseException {
        String query = "select id, role from users where username = ?;";
        try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
            preparedStatement.setString(1, entity.getUsername());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            entity.setId(resultSet.getInt("id"));
            entity.setRole(resultSet.getString("role"));
            return entity;
        } catch (SQLException e) {
            throw new DatabaseException("Error while executing query: " + e.getMessage());
        }
    }

    public String getPasswordHash(UserEntity entity) throws DatabaseException {
        String query = "select hashpassword from users where username = ?;";
        try (PreparedStatement preparedStatement = getPreparedStatement(query)) {
            preparedStatement.setString(1, entity.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }
            return resultSet.getString("hashpassword");
        } catch (SQLException e) {
            throw new DatabaseException("Error while executing query: " + e.getMessage());
        }
    }
}
