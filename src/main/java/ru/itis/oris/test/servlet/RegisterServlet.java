package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.test.model.UserDao;
import ru.itis.oris.test.service.AuthService;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final AuthService authService = new AuthService();
    private final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        if (username == null || username.trim().isEmpty()) {
            req.setAttribute("error", "Имя пользователя обязательно");
            req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
            return;
        }

        if (password == null || password.length() < 6) {
            req.setAttribute("error", "Пароль должен быть не менее 6 символов");
            req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
            return;
        }

        if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Пароли не совпадают");
            req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
            return;
        }

        if (userDao.findByUsername(username).isPresent()) {
            req.setAttribute("error", "Пользователь с таким именем уже существует");
            req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
            return;
        }

        authService.register(username, password, "USER");
        resp.sendRedirect(req.getContextPath() + "/login?registered=true");
    }
}