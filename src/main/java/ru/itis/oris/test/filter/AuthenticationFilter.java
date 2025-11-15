package ru.itis.oris.test.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.test.model.User;

import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    private static final List<String> PUBLIC_PATHS = List.of(
            "/index", "/login", "/register", "/css/", "/js/", "/jsp/partial/"
    );

    private static final List<String> USER_PATHS = List.of(
            "/books", "/borrow", "/my-books", "/return"
    );

    private static final List<String> ADMIN_PATHS = List.of(
            "/admin", "/books/edit", "/books/delete", "/books/create", "/users"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        if (isPublicPath(path)) {
            chain.doFilter(request, response);
            return;
        }

        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        if (isAdminPath(path) && !"ADMIN".equals(user.getRole())) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isPublicPath(String path) {
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }

    private boolean isAdminPath(String path) {
        return ADMIN_PATHS.stream().anyMatch(path::startsWith);
    }
}