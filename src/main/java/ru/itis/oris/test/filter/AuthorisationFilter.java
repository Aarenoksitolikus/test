package ru.itis.oris.test.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Этот фильтр перехватывает все запросы ("/*") в приложение
 * и проверяет наличие в сессии объекта user.
 * <p>
 * Если в сессии есть активный пользователь, пропускает запрос
 * дальше по цепочке фильтров (FilterChain)
 * <p>
 * Если нет, перенаправляет запрос на главную страницу (index)
 * <p>
 * Не отрабатывает на общедоступные URL'ы
 */
@WebFilter("/*")
public class AuthorisationFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        String requestURI = ((HttpServletRequest) req).getRequestURI();

        if (requestURI.equals("/index")
                || requestURI.equals("/registration")
                || requestURI.equals("/login")) {
            chain.doFilter(req, resp);
        } else {
            HttpSession session = ((HttpServletRequest) req).getSession(false);

            if (session == null || session.getAttribute("user") == null) {
                ((HttpServletResponse) resp).sendRedirect("/registration");
            } else {
                chain.doFilter(req, resp);
            }
        }
    }
}
