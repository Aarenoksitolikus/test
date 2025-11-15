package ru.itis.oris.test.filter;


import ru.itis.oris.test.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("user");
        }

        // Public pages
        if (path.startsWith("/auth/") || path.equals("/") || path.equals("")) {
            chain.doFilter(request, response);
            return;
        }

        // Check authentication
        if (user == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/auth/login");
            return;
        }

        // Check authorization for admin pages
        if (path.startsWith("/admin") || path.startsWith("/books/")) {
            if (user.getRole() != User.Role.ADMIN) {
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}