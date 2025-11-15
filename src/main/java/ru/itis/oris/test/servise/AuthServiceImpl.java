package ru.itis.oris.test.servise;
import org.mindrot.jbcrypt.BCrypt;
import ru.itis.oris.test.model.User;
import ru.itis.oris.test.model.UserDao;

import java.util.Optional;

public class AuthServiceImpl implements AuthService {

    private final UserDao userDao;

    public AuthServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String username, String password) {
        Optional<User> userOpt = userDao.findByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (BCrypt.checkpw(password, user.getHashPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean register(String username, String password) {
        // запрещаю старые пароли
        if (password.length() < 6) return false;

        if (userDao.findByUsername(username).isPresent()) {
            return false;
        }

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

        User user = new User(null, username, hashed, "USER");
        userDao.save(user);
        return true;
    }
}