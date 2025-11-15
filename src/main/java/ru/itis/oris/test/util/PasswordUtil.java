package ru.itis.oris.test.util;

public class PasswordUtil {
    public static String hash(String password) {
        return String.valueOf(password.hashCode());
    }

    public static boolean check(String password, String hash) {
        return hash.equals(String.valueOf(password.hashCode()));
    }
}