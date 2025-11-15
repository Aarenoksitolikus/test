package ru.itis.oris.test.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getRequestURI();

        // Разрешаем доступ к публичным страницам
        if (path.startsWith("/css/") || path.startsWith("/js/") ||
                path.equals("/login") || path.equals("/register") ||
                path.equals("/") || path.equals("/index.jsp") || path.equals("/menu") ||
                path.equals("/logout") || path.equals("/home")) {
            chain.doFilter(request, response);
            return;
        }

        // Проверяем авторизацию для защищенных страниц
        HttpSession session = httpRequest.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            httpResponse.sendRedirect("/login");
            return;
        }

        chain.doFilter(request, response);
    }
}