package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.model.User;
import ru.itis.oris.service.AuthService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class AuthServlet extends HttpServlet {
    private AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Показываем форму логина
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<User> user = authService.authenticate(username, password);

        if (user.isPresent()) {
            // Создаем сессию и сохраняем пользователя
            HttpSession session = request.getSession();
            session.setAttribute("user", user.get());
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            request.setAttribute("error", "Неверные логин или пароль");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}
