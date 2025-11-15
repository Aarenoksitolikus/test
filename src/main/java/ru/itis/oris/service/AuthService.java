package ru.itis.oris.service;

import ru.itis.oris.model.User;
import ru.itis.oris.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

public class AuthService {
    private UserRepository userRepository = new UserRepository();

    public Optional<User> authenticate(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (password.equals("password")) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    public boolean register(String username, String password, User.Role role) {
        // Проверяем, нет ли уже пользователя с таким именем
        if (userRepository.findByUsername(username).isPresent()) {
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setHashPassword(password);
        user.setRole(role);

        return userRepository.save(user);
    }

    // Метод для создания тестовых пользователей
    public void createTestUsers() {
        register("admin", "admin", User.Role.ADMIN);
        register("user1", "user1", User.Role.USER);
        register("user2", "user2", User.Role.USER);
    }
}
