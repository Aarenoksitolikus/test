package filter;

import model.Role;
import model.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (user.getRole() == Role.ADMIN) {
                chain.doFilter(request, response);
                return;
            }
        }

        
        httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Доступ запрещен");
    }
}