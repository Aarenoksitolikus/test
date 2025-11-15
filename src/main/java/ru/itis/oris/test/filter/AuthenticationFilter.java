package ru.itis.oris.test.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String path = req.getRequestURI();
        HttpSession session = req.getSession();
        System.out.println(path);

        if (path == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        if (isPublicRecourse(path)) {
            chain.doFilter(req, res);
            return;
        }

        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        chain.doFilter(req, res);

    }

    private boolean isPublicRecourse(String path) {
        return  path.endsWith("/register") ||
                path.endsWith("/index") ||
                path.endsWith("/login") ||
                path.endsWith(".jsp") ||
                path.endsWith(".css") ||
                path.endsWith(".js");
    }
}
