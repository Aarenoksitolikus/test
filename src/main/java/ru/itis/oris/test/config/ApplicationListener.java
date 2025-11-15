package ru.itis.oris.test.config;

import ru.itis.oris.test.service.UserService;
import ru.itis.oris.test.service.UserServiceImpl;
import ru.itis.oris.test.util.DataManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        DataManager dataClass = new DataManager();
        UserService userService = new UserServiceImpl(dataClass);

        context.setAttribute("userService", userService);
    }
}