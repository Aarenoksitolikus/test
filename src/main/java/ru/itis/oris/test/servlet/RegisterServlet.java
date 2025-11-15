package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.test.dao.UserDao;
import ru.itis.oris.test.model.User;
import ru.itis.oris.test.util.PasswordUtils;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (!isValidUsername(username) || !isValidPassword(password)) {
            System.out.println(username + " " + isValidUsername(username));
            System.out.println(password + " " + isValidPassword(password));
            resp.sendRedirect("register.jsp?error=invalid");
            return;
        }

        if (userDao.findByUsername(username).isPresent()) {
            resp.sendRedirect("register.jsp?error=exists");
            return;
        }

        String hash = PasswordUtils.hashPassword(password);
        User user = new User(username, hash, "USER");
        userDao.save(user);

        resp.sendRedirect("login.jsp?success=1");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/register.jsp").forward(req, resp);
    }

    private static boolean isValidUsername(String username) {
        return username.toCharArray().length > 6;
    }

    private static boolean isValidPassword(String password) {
        return password.length() >= 6;
    }
}
