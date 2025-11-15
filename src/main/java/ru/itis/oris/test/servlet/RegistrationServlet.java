package ru.itis.oris.test.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.oris.test.dao.UserDao;
import ru.itis.oris.test.model.User;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User us = new User();
            us.setUsername(username);
            us.setHashPassword(password);

            UserDao userDao = new UserDao();
            userDao.save(us);

            response.sendRedirect("login");
        } catch (Exception e) {
            request.setAttribute("error", "Ошибка регистрации");
            request.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
        }
    }
}