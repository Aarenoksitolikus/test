package ru.itis.oris.test.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.oris.test.dao.PostDao;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PostDao postDao = new PostDao();
            var posts = postDao.findAll();

            // Логируем для отладки
            System.out.println("Найдено постов: " + posts.size());

            request.setAttribute("posts", posts);
            request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Ошибка: " + e.getMessage());
        }
    }
}