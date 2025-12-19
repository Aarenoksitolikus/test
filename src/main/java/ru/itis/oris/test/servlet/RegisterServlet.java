package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.test.servise.AuthService;
import ru.itis.oris.test.util.ServiceProvider;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

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

        boolean ok = authService.register(username, password);

        if (!ok) {
            req.setAttribute("error", "Ошибка в регистрации");
            req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect("/login");
    }
}