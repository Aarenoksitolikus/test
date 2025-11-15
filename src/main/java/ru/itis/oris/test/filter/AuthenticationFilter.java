package ru.itis.oris.test.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.test.model.User;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    private final List<String> PUBLIC_PATHS = Arrays.asList(
        "/",
        "/index.jsp",
        "/login",
        "/register",
        "/posts",
        "/css/",
        "/js/",
        "/images/"
    );

    private final List<String> USER_PATHS = Arrays.asList(
        "/posts/create",
        "/posts/edit",
        "/posts/delete",
        "/comments/create"
    );

    private final List<String> MODERATOR_PATHS = Arrays.asList(
        "/comments/delete"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        if (isPublicResource(path)) {
            chain.doFilter(request, response);
            return;
        }

        if (isPublicPath(path)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            if (requiresAuthentication(path)) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
                return;
            }
        } else {
            if (!hasAccess(user, path)) {
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Доступ запрещен");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isPublicResource(String path) {
        return path.startsWith("/css/") ||
            path.startsWith("/js/") ||
            path.startsWith("/images/") ||
            path.endsWith(".css") ||
            path.endsWith(".js") ||
            path.endsWith(".png") ||
            path.endsWith(".jpg") ||
            path.endsWith(".jpeg");
    }

    private boolean isPublicPath(String path) {
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }

    private boolean requiresAuthentication(String path) {
        return USER_PATHS.stream().anyMatch(path::startsWith) ||
            MODERATOR_PATHS.stream().anyMatch(path::startsWith);
    }

    private boolean hasAccess(User user, String path) {
        String role = user.getRole();

        if ("MODERATOR".equals(role)) {
            return true;
        }

        if ("USER".equals(role)) {
            if (MODERATOR_PATHS.stream().anyMatch(path::startsWith)) {
                return false;
            }
            return true;
        }

        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}

