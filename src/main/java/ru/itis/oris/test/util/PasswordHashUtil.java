package ru.itis.oris.test.util;

import org.mindrot.jbcrypt.BCrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashUtil {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}