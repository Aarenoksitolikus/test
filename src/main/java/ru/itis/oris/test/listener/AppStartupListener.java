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
        System.out.println("Application starting...");

        try {
            Thread.sleep(1000);

            UserDao userDao = new UserDao();
            String adminUsername = "admin";
            String adminPassword = "sosal";

            userDao.findByUsername(adminUsername).ifPresentOrElse(
                u -> System.out.println("Модератор уже существует: " + u.getUsername()),
                () -> {
                    try {
                        String hash = PasswordUtils.hashPassword(adminPassword);
                        User admin = new User(adminUsername, hash, "MODERATOR");
                        userDao.save(admin);
                        System.out.println("Модератор создан: " + adminUsername);
                    } catch (Exception e) {
                        System.err.println("Ошибка при создании модератора: " + e.getMessage());
                        e.printStackTrace();
                    }
                });
        } catch (Exception e) {
            System.err.println("Ошибка в AppStartupListener: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application shutting down...");
    }
}
