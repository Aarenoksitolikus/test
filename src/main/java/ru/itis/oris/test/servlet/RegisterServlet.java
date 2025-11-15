package ru.itis.oris.test.servlet;

import ru.itis.oris.test.service.AuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private AuthService authService = new AuthService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/auth/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirmPassword");

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Заполните все поля");
            request.getRequestDispatcher("/jsp/auth/register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirm)) {
            request.setAttribute("error", "Пароли не совпадают");
            request.getRequestDispatcher("/jsp/auth/register.jsp").forward(request, response);
            return;
        }

        if (authService.register(username, password)) {
            response.sendRedirect("/login?success=Регистрация успешна");
        } else {
            request.setAttribute("error", "Пользователь уже существует");
            request.getRequestDispatcher("/jsp/auth/register.jsp").forward(request, response);
        }
    }
}