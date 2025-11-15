package ru.itis.oris.test.servlet;

import ru.itis.oris.test.model.User;
import ru.itis.oris.test.service.AuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private AuthService authService = new AuthService(); protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/auth/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Заполните все поля");
            request.getRequestDispatcher("/jsp/auth/login.jsp").forward(request, response);
            return;
        }

        Optional<User> user = authService.login(username, password);
        if (user.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user.get());
            response.sendRedirect("/menu");
        } else {
            request.setAttribute("error", "Неверные данные");
            request.getRequestDispatcher("/jsp/auth/login.jsp").forward(request, response);
        }
    }
}
