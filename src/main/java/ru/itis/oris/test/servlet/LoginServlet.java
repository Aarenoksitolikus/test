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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getParameter("error") != null) {
            req.setAttribute("errorMessage", "Неверный логин или пароль");
        }
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String userName = req.getParameter("user_name");
        String password = req.getParameter("password");

        try {
            User user = AuthService.authenticate(userName, password);
            if (user == null) {
                System.out.println("❌ Invalid credentials for: " + userName);
                // ИСПРАВЛЕНО: правильный путь редиректа при ошибке
                resp.sendRedirect(req.getContextPath() + "/login?error=true");
                return;
            }

            HttpSession session = req.getSession(true);
            session.setAttribute("user_id", user.getId());
            session.setAttribute("user_name", user.getUserName());
            session.setAttribute("role", user.getRole());
            resp.sendRedirect(req.getContextPath() + "/main");
        } catch (Exception e) {
            System.out.println("💥 LoginServlet: Error during authentication - " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}