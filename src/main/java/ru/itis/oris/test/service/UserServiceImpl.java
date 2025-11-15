package ru.itis.oris.test.service;

import ru.itis.oris.test.model.UserEntity;
import ru.itis.oris.test.util.DataManager;

public class UserServiceImpl implements UserService {
    private DataManager dataManager;

    @Override
    public UserEntity authenticateUser(String username, String password) {
        UserEntity entity = UserData.findUserByUsername(username);
        if (entity != null && checkPassword(password, entity.getPasswordHash())) {
            return entity;
        }
        return null;
    }
}