package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.test.service.UserService;
import ru.itis.oris.test.util.HashUtil;
import ru.itis.oris.test.util.UserDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService;
    private UserDAO userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String pass = req.getParameter("password");

        String errorMsg = "";

        if (username == null || username.trim().isEmpty()) {
            errorMsg += "Логин не может быть пустым ";
        }
        if (pass == null || pass.trim().isEmpty()) {
            errorMsg += "Пароль не может быть пустым ";
        }
        try {
            if (userService.isUserExist(username)) {
                errorMsg += "Юзер существует ";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (!errorMsg.isEmpty()) {
            resp.getWriter().write(errorMsg);
            return;
        }

        pass = HashUtil.hashPassword(pass);

        try {
            userService.saveNewUser(username,pass);

            Cookie userCookie = new Cookie("user", username);

            userCookie.setMaxAge(60 * 60 * 24);

            userCookie.setPath("/");

            resp.addCookie(userCookie);

            resp.sendRedirect(req.getContextPath() + "/username");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init() throws ServletException {
        this.userService = new UserService();
    }
}
