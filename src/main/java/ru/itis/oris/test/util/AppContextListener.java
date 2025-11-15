package ru.itis.oris.test.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Statement;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Пример: выполнить скрипт resources/db/init.sql при старте
        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement()) {
            String sql = new String(Files.readAllBytes(Path.of(sce.getServletContext().getRealPath("/WEB-INF/classes/db/init.sql"))));
            for (String stmt : sql.split(";")) {
                if (!stmt.trim().isEmpty()) st.execute(stmt);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override public void contextDestroyed(ServletContextEvent sce) {}
}
