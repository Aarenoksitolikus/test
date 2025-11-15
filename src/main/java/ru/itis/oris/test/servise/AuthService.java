package ru.itis.oris.test.servise;
import ru.itis.oris.test.model.User;

public class AuthService {
    public User login(String username, String password);
    public boolean register(String username, String password);
}
