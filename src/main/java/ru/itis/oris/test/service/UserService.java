package ru.itis.oris.test.service;


import ru.itis.oris.test.model.UserEntity;

public interface UserService {
    UserEntity authenticateUser(String username, String password);
    UserEntity saveUser(UserEntity user);
}


