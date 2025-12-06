package ru.itis.oris.test.model;


import lombok.NoArgsConstructor;

import java.sql.*;

/**
 * Рано или поздно этот класс должен быть преобразован в репозитории...
 */
@NoArgsConstructor
public class DataClass {
    private static final String GET_USER_BY_CREDS = "select * from usr where username = ? and hash_password = ?";

    public void test(String username) {
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement("select * from usr where username = ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public UserEntity getUser(long id) throws SQLException {
        return convertFromResultSet(getStatement()
                .executeQuery("select * from usr where id = " + id + ";"));
    }

    public boolean isUserExist(String username) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_USER_BY_CREDS)) {
            preparedStatement.setString(1, username);
            preparedStatement.executeQuery();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
    }

    public UserEntity getUser(String username) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("select * from usr u where u.username = ?")) {
            preparedStatement.setString(1, username);
            return convertFromResultSet(preparedStatement.executeQuery());
        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }
    }

    public void saveNewUser(String username, String email, String password) throws SQLException {
        String sql = "INSERT INTO usr (username, email, hash_password) VALUES (?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

//не безопастно от sql инъекций
//    public void updateUser(String username) throws SQLException {
//        getStatement().executeUpdate("""
//                update usr
//                set username = '""" + username + "' where username = " + username + ";");
//    }

    public void deleteUserById(Long id) throws SQLException {
        getStatement().executeUpdate("delete from usr where id = 1");
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        // это костыль, должно работать и без этого, НЕОБХОДИМО РАЗОБРАТЬСЯ!!!
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/WatchOnLine",
                "postgres", "postgres");
    }

    private UserEntity convertFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return new UserEntity(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("hashPassword")
            );
        }

        return null;
    }

    private Statement getStatement() {
        try {
            return getConnection().createStatement();
        } catch (SQLException e) {
            System.out.println("ваще всё поломалось, Наташа, мы ваще всё уронили");
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
