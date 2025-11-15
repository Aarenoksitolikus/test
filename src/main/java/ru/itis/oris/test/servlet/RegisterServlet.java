package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.model.User;
import ru.itis.oris.service.AuthService;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Проверка подтверждения пароля
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Пароли не совпадают");
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
            return;
        }

        // Проверка длины пароля
        if (password.length() < 4) {
            request.setAttribute("error", "Пароль должен быть не менее 4 символов");
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
            return;
        }

        // Регистрация пользователя
        boolean success = authService.register(username, password, User.Role.USER);

        if (success) {
            request.setAttribute("success", "Регистрация успешна! Теперь вы можете войти.");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Пользователь с таким именем уже существует");
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
        }
    }
}
