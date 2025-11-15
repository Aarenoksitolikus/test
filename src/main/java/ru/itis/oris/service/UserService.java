package ru.itis.oris.service;

import ru.itis.oris.model.User;
import ru.itis.oris.repository.UserRepository;

import java.util.Optional;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean createUser(String username, String password, User.Role role) {
        User user = new User();
        user.setUsername(username);
        user.setHashPassword(password);
        user.setRole(role);

        return userRepository.save(user);
    }

    public boolean isUsernameAvailable(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }
}
