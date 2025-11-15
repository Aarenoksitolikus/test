package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.test.model.User;
import ru.itis.oris.test.servise.AuthService;
import ru.itis.oris.test.util.ServiceProvider;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AuthService authService;

    @Override
    public void init() {
        authService = ServiceProvider.getInstance().getAuthService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = authService.login(username, password);

        if (user == null) {
            req.setAttribute("error", "Неверный логин или пароль");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            return;
        }

        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/index");
    }
}

