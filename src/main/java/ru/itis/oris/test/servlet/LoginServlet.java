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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Optional<User> userOpt = userDao.findByEmail(email);
        if (userOpt.isEmpty()) {
            resp.sendRedirect("login.jsp?error=notfound");
            return;
        }

        User user = userOpt.get();
        if (!PasswordUtils.checkPassword(password, user.getPasswordHash())) {
            resp.sendRedirect("login.jsp?error=wrongpass");
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        if ("admin".equals(user.getRole())) {
            resp.sendRedirect("admin/dashboard");
        } else {
            resp.sendRedirect("index");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
