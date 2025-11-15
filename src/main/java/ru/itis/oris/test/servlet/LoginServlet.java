package ru.itis.oris.test.servlet;

import ru.itis.oris.test.model.entities.User;
import ru.itis.oris.test.service.AuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // server-side validation
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            req.setAttribute("error", "Введите логин и пароль");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
            return;
        }

        Optional<User> userOpt = authService.authenticate(username, password);
        if (userOpt.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("currentUser", userOpt.get());
            resp.sendRedirect(req.getContextPath() + "/posts");
        } else {
            req.setAttribute("error", "Неверные учетные данные");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }
    }
}
