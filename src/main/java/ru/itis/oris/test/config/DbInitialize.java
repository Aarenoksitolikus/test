package ru.itis.oris.test.config;

import com.slavikjunior.deorm.db_manager.DbConnectionManager;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DbInitialize implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DbConnectionManager.INSTANCE.configure(
                "localhost",
                "5432",
                "oris",
                "postgres",
                "7913"
        );
    }
}
