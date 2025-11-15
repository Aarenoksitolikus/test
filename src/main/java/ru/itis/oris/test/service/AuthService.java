package ru.itis.oris.test.service;

import com.slavikjunior.deorm.orm.EntityManager;
import ru.itis.oris.test.model.Role;
import ru.itis.oris.test.model.User;
import ru.itis.oris.test.util.PasswordHashUtil;

import java.util.List;
import java.util.Map;

public class AuthService {

    public static User authenticate(String userName, String password) {
        try {
            System.out.println("🔧 Auth attempt for: " + userName);
            User user = EntityManager.INSTANCE.getUnique(User.class, Map.of("username", userName));
            if (user != null) {
                String hashedPassword = PasswordHashUtil.hashPassword(password);
                if (user.getHashedPassword().equals(hashedPassword)) {
                    return user;
                }
            } else {
                System.out.println("❌ User not found: " + userName);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isUserNameExists(String userName) {
        try {
            var users = EntityManager.INSTANCE.get(User.class, Map.of("username", userName));
            return users != null && !users.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isModerator(String userName) {
        try {
            var users = EntityManager.INSTANCE.get(User.class, Map.of("username", userName));
            return users != null && !users.isEmpty() && users.get(0).getRole().equals(Role.moderator.name());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}