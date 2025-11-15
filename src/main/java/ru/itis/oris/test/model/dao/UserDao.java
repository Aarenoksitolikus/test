package ru.itis.oris.test.model.dao;

import ru.itis.oris.test.model.entity.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.itis.oris.test.util.DBconfig.prepareStatement;

public class UserDao implements CRUDinterface<UserEntity> {
    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM usr WHERE id = ?;";
    private static final String SQL_AUTH = "SELECT * FROM usr WHERE email = ? AND password = ?;";
    private static final String SQL_INSERT = "INSERT INTO usr VALUES (?, ?, ?, ?);";
    private static final String SQL_DELETE_ONE = "DELETE FROM usr WHERE id = ?;";

    @Override
    public boolean create(UserEntity entity) {
        try{
            PreparedStatement preparedStatement = prepareStatement(SQL_INSERT);
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getUsername());
            preparedStatement.setString(3, entity.getHashPassword());
            preparedStatement.setString(4, entity.getRole());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public UserEntity get(long id) {
        try {
            PreparedStatement preparedStatement = prepareStatement(SQL_GET_USER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            return convertFromResultSet(rs);
        } catch (SQLException e) {
            return null;
        }
    }
    public UserEntity get(String email, String password){
        try {
            PreparedStatement preparedStatement = prepareStatement(SQL_AUTH);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            return convertFromResultSet(rs);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean update(UserEntity entity) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        try {
            PreparedStatement preparedStatement = prepareStatement(SQL_DELETE_ONE);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            return false;
        }
    }
    private UserEntity convertFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return new UserEntity(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role")
            );
        }
        return null;
    }
}
