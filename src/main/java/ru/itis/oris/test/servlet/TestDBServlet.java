package ru.itis.oris.test.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.oris.test.util.DatabaseUtil;
import java.io.IOException;
import java.sql.*;

@WebServlet("/test-db")
public class TestDBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection conn = DatabaseUtil.getConnection()) {
            response.getWriter().write("Подключение к БД успешно!\n");

            // Проверяем таблицы
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet tables = meta.getTables(null, null, "users", new String[]{"TABLE"});

            if (tables.next()) {
                response.getWriter().write("Таблица 'users' существует\n");
            } else {
                response.getWriter().write("Таблица 'users' не найдена\n");
            }

        } catch (Exception e) {
            response.getWriter().write("Ошибка подключения: " + e.getMessage());
        }
    }
}