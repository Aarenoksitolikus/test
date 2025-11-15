package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.test.model.dao.UserDao;
import ru.itis.oris.test.model.entity.UserEntity;
import ru.itis.oris.test.service.UserService;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {
        this.userService = new UserService(new UserDao());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("html/registration_page.html").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");

        if (username == null || username.trim().isBlank()
                || password1 == null || password1.isEmpty()
                || password2 == null || password2.isEmpty()) {
            resp.sendRedirect("/login?error=Username and password are required");
        }
        if (!password1.equals(password2)){
            resp.sendRedirect("/login?error=passwords are not match");
        }
        UserEntity user = new UserEntity(0, username, password1, "not admin");
        userService.registrateUser(user);
    }
}
