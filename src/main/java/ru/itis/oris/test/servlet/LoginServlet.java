package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.test.model.User;
import ru.itis.oris.test.service.AuthService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Optional<User> userOpt = authService.login(username, password);

        if (userOpt.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("user", userOpt.get());
            resp.sendRedirect(req.getContextPath() + "/index");
        } else {
            req.setAttribute("error", "Неверное имя пользователя или пароль");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }
    }
}