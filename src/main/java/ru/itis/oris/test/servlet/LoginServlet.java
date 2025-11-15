package ru.itis.oris.test.servlet;

import ru.itis.oris.test.model.dao.UserDao;
import ru.itis.oris.test.model.entity.UserEntity;
import ru.itis.oris.test.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() {
        this.userService = new UserService(new UserDao());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("html/login_page.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.trim().isBlank()
                || password == null || password.isEmpty()) {
            resp.sendRedirect("/login?error=Username and password are required");
        }

        UserEntity user = userService.authenticateUser(username, password);

        if (user == null) {
            resp.sendRedirect("/login?error=User does not exist");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            resp.sendRedirect("/index");
        }
    }
}

