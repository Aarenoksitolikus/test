package ru.itis.oris.test.util;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptPasswordEncoder {

    public static String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean matches(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }
}
