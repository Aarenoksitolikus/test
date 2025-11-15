package ru.itis.oris.test.service;

import lombok.RequiredArgsConstructor;
import ru.itis.oris.test.model.User;
import ru.itis.oris.test.repository.UserRepository;
import ru.itis.oris.test.util.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(String username, String password) {
        String hashPassword = BCryptPasswordEncoder.encode(password);
        User user = new User(null, username, hashPassword, "USER");
        return userRepository.save(user);
    }

    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        return BCryptPasswordEncoder.matches(password, user.getHashPassword());
    }

}
