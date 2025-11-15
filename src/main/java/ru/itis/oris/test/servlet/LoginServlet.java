package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.test.dao.UserDao;
import ru.itis.oris.test.model.User;
import ru.itis.oris.test.util.PasswordUtils;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            resp.sendRedirect("login.jsp?error=empty");
            return;
        }

        Optional<User> userOptional = userDao.findByUsername(username.trim());

        if (userOptional.isEmpty()) {
            resp.sendRedirect("login.jsp?error=invalid");
            return;
        }

        User user = userOptional.get();

        if (!PasswordUtils.checkPassword(password, user.getPasswordHash())) {
            resp.sendRedirect("login.jsp?error=invalid");
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(30 * 60);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
