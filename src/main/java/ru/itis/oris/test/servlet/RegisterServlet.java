package ru.itis.oris.test.servlet;

import com.slavikjunior.deorm.orm.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.test.model.Role;
import ru.itis.oris.test.model.User;
import ru.itis.oris.test.service.AuthService;
import ru.itis.oris.test.util.PasswordHashUtil;

import java.io.IOException;

@WebServlet(urlPatterns = {"/register", "/register/*"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String err = req.getParameter("error");
        if ("missing".equals(err)) {
            req.setAttribute("errorMessage", "Заполните все поля");
        } else if ("duplicate".equals(err)) {
            req.setAttribute("errorMessage", "Логин или email уже занят");
        } else if (err != null) {
            req.setAttribute("errorMessage", "Ошибка регистрации");
        }
        req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String userName = req.getParameter("user_name");
        String password = req.getParameter("password");

        if (userName == null || password == null ||
                userName.isBlank() || password.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/register?error=missing");
            return;
        }

        try {
            if (AuthService.isUserNameExists(userName)) {
                throw new Exception("UserName already exists");
            }

            String hashedPassword = PasswordHashUtil.hashPassword(password);

            boolean isModerator = AuthService.isModerator(userName);

            User user = new User(0, userName, hashedPassword, isModerator ? Role.moderator.name() : Role.user.name());
            user = EntityManager.INSTANCE.create(user);

            HttpSession session = req.getSession(true);
            session.setAttribute("user_id", user.getId());
            session.setAttribute("user_name", user.getUserName());
            session.setAttribute("role", user.getRole());
            resp.sendRedirect(req.getContextPath() + "/main");

        } catch (Exception e) {
            System.out.println("💥 RegisterServlet: Error registering user - " + e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/register?error=duplicate");
        }
    }
}