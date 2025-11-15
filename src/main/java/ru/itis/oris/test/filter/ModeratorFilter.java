package ru.itis.oris.test.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.test.model.Role;

import java.io.IOException;

@WebFilter(urlPatterns = {"/remove", "/remove/*"})
public class ModeratorFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);

        // Проверяем сессию и роль
        if (session == null || session.getAttribute("role") == null) {
            res.sendRedirect(req.getContextPath() + "/auth");
            return;
        }

        String role = session.getAttribute("role").toString();
        if (!role.equals(Role.moderator.name())) {
            res.sendRedirect(req.getContextPath() + "/auth");
            return;
        }

        chain.doFilter(req, res);
    }
}