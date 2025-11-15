package ru.itis.oris.test.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.test.model.UserEntity;

import java.io.IOException;

@WebFilter("/*")
public class AuthorisationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String path = request.getRequestURI();

        if (path.equals("/") ||
                path.startsWith("/index") ||
                path.startsWith("/login") ||
                path.startsWith("/register")
                && request.getMethod().equals("GET")) {

            chain.doFilter(req, resp);
            return;
        }

        HttpSession session = request.getSession(false);
        UserEntity user = (session != null) ? (UserEntity) session.getAttribute("USER") : null;

        if (user == null) {
            response.sendRedirect("/login");
            return;
        }

        chain.doFilter(req, resp);
    }
}
