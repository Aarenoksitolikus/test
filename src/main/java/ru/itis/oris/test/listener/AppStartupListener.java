package ru.itis.oris.test.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.itis.oris.test.dao.UserDao;
import ru.itis.oris.test.model.User;
import ru.itis.oris.test.util.PasswordUtils;

@WebListener
public class AppStartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserDao userDao = new UserDao();
        String adminUsername = "admin";
        String adminPassword = "sosal";

        userDao.findByUsername(adminUsername).ifPresentOrElse(
            u -> System.out.println("Админ уже существует."),
            () -> {
                String hash = PasswordUtils.hashPassword(adminPassword);
                User admin = new User(adminUsername, hash, "admin");
                userDao.save(admin);
                System.out.println("Админ создан");
            });
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}

}
