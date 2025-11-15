package ru.itis.oris.test.servlet;

import model.User;

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
        
        // Public paths
        if (path.startsWith("/auth/") || path.equals("/") || path.equals("/menu") || 
            path.startsWith("/css/") || path.startsWith("/js/")) {
            chain.doFilter(request, response);
            return;
        }
        
        // Check if user is authenticated
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            request.setAttribute("currentUser", user);
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/auth/login");
        }
    }
}