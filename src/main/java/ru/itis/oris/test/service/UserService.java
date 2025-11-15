package ru.itis.oris.test.service;

import lombok.RequiredArgsConstructor;
import ru.itis.oris.test.converter.UserConverter;
import ru.itis.oris.test.dao.UserDao;
import ru.itis.oris.test.entity.UserEntity;
import ru.itis.oris.test.model.User;
import ru.itis.oris.test.util.HashUtil;
import ru.itis.oris.test.util.validator.UserValidator;
import ru.itis.oris.test.util.validator.Validator;

@RequiredArgsConstructor
public class UserService {
    private final UserDao dao;
    private final UserConverter converter;
    private final Validator<User> validator = new UserValidator();

    public UserEntity registerUser(User user) throws IllegalArgumentException {
        if (!validator.validate(user)) {
            throw new IllegalArgumentException("Invalid user data");
        }
        return dao.create(converter.convert(user));
    }

    public UserEntity authenticateUser(User user) throws IllegalArgumentException {
        if (!validator.validate(user)) {
            throw new IllegalArgumentException("Invalid user data");
        }
        UserEntity entity = converter.convert(user);
        if (HashUtil.matches(user.getPassword(), dao.getPasswordHash(entity))) {
            return dao.getUser(entity);
        }
        return null;
    }
}
