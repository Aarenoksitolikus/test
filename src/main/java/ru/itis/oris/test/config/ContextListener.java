package ru.itis.oris.test.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.itis.oris.test.converter.UserConverter;
import ru.itis.oris.test.dao.UserDao;
import ru.itis.oris.test.service.UserService;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserService userService = new UserService(
                new UserDao(), new UserConverter()
        );
        sce.getServletContext().setAttribute("userService", userService);
    }
}
