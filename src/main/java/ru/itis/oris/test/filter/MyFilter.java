package ru.itis.oris.test.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class MyFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        String requestURI = ((HttpServletRequest) req).getRequestURI();

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = ((HttpServletRequest) req).getServletPath();

        if (requestURI.startsWith(request.getContextPath() + "/css") ||
                requestURI.startsWith(request.getContextPath() + "/js") ||
                requestURI.startsWith(request.getContextPath() + "/images") ||
                requestURI.endsWith(".png") ||
                requestURI.endsWith(".jpg") ||
                requestURI.endsWith(".gif")) {
            chain.doFilter(req, res);
            return;
        }


    }
}
