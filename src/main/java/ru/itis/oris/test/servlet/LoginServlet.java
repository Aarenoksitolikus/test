package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.test.entity.UserEntity;
import ru.itis.oris.test.model.User;
import ru.itis.oris.test.service.UserService;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService service;

    @Override
    public void init() {
        service = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User(null, username, password, "user");

        try {
            UserEntity authenticatedUser = service.authenticateUser(user);
            if (authenticatedUser == null) {
                resp.sendRedirect("/login?error=Invalid credentials");
                return;
            }
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("/index");
        } catch (IllegalArgumentException e) {
            resp.sendError(400, e.getMessage());
        }
    }
}
