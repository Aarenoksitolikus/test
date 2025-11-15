package ru.itis.oris.test.servlet;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.oris.test.dao.UserDao;
import ru.itis.oris.test.model.User;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserDao userDao = new UserDao();
            if (userDao.checkPassword(username, password)) {
                User user = userDao.findByUsername(username).get();
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("index");
            } else {
                request.setAttribute("error", "Неверные данные");
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
