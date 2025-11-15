package ru.itis.oris.test.filter;


import ru.itis.oris.test.model.User;
import ru.itis.oris.test.service.AuthService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Create default admin user if not exists
        AuthService authService = new AuthService();
        if (!authService.register("admin", "Admin123!", User.Role.ADMIN)) {
            System.out.println("Admin user already exists or could not be created");
        } else {
            System.out.println("Default admin user created: admin/Admin123!");
        }

        System.out.println("Library Management System initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Library Management System shutdown");
    }
}