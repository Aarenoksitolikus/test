package ru.itis.oris.test.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

@WebFilter("/*")
public class LogFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String uri = req.getRequestURI();
        LocalDateTime time = LocalDateTime.now();

        System.out.println("log: " + "session id = " + session.getId() + " \n" + "time = " + time + "\n" + "URI = " + uri + "\n");
        chain.doFilter(req, res);
    }
}