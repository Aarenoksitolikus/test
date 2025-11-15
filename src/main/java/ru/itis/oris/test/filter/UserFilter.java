package ru.itis.oris.test.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.test.model.Role;

import java.io.IOException;

@WebFilter(urlPatterns = {"/crete", "/crete/*", "/edit", "/edit/*", "/comment", "/comment/*"})
public class UserFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        super.doFilter(req, res, chain);

        var paramMap = req.getParameterMap();
        var role = paramMap.get("role");

        if (role != null && role[0].equals(Role.unknown_user.name())) {
            res.sendRedirect(req.getContextPath() + "/auth");
        }
    }
}
