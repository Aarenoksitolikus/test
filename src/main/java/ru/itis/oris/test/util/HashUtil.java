package ru.itis.oris.test.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Этот утилитный класс должен реализовывать
 * единственный метод -- хэширование пароля
 */
public class HashUtil {
    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt(12); // cost 12
        return BCrypt.hashpw(password, salt);
    }

    public static boolean verify(String raw, String hashed) {
        return BCrypt.checkpw(raw, hashed);
    }
}