package ru.itis.oris.test.service;


import lombok.RequiredArgsConstructor;
import ru.itis.oris.test.model.DataClass;
import ru.itis.oris.test.model.UserEntity;
import ru.itis.oris.test.util.HashUtil;
import java.sql.SQLException;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final DataClass dataClass;

    public UserEntity authenticateUser(String username, String password) {
        String hashPassword = HashUtil.hashPassword(password);
        UserEntity user = dataClass.getUser(username);
        if (HashUtil.verify(password, user.getHashPassword())) {
            return user;
        }

        return null;
    }

    public boolean saveUserInDb(String username, String email, String password) {

        try {
            dataClass.createUserTable();
            dataClass.saveNewUser(username, email, password);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isUserExist(String username){
        return dataClass.isUserExist(username);
    }
}
