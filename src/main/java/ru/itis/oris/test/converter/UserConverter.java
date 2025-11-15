package ru.itis.oris.test.converter;

import ru.itis.oris.test.entity.UserEntity;
import ru.itis.oris.test.model.User;
import ru.itis.oris.test.util.HashUtil;

public class UserConverter implements Converter<User, UserEntity> {
    @Override
    public UserEntity convert(User model) {
        return UserEntity.builder()
                .id(model.getId())
                .username(model.getUsername())
                .hashPassword(HashUtil.hashPassword(model.getPassword()))
                .role(model.getRole())
                .build();
    }
}
