package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        boolean isLoggedIn = session != null && session.getAttribute("user_id") != null;

        if (isLoggedIn) {
            resp.sendRedirect(req.getContextPath() + "/main");
        } else {
            req.getRequestDispatcher("/jsp/auth.jsp").forward(req, resp);
        }
    }
}
