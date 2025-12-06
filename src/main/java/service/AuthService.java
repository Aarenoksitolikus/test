package service;

import dao.UserDao;
import model.User;
import model.Role;
import util.PasswordHasher;


public class AuthService {
    private final UserDao userDao = new UserDao();
    
    public User login(String username, String password) {
        User user = userDao.findByUsername(username).orElse(null);
        
        if (user != null && PasswordHasher.checkPassword(password, user.getHashPassword())) {
            return user;
        }
        return null;
    }
    
    public boolean register(String username, String password, Role role) {
        if (userDao.findByUsername(username).isPresent()) {
            return false; // User already exists
        }
        
        String hashedPassword = PasswordHasher.hashPassword(password);
        User user = new User(username, hashedPassword, role);
        userDao.save(user);
        return true;
    }
}