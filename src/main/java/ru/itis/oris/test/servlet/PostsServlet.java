package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.test.service.PostService;

import java.io.IOException;

@WebServlet("/posts")
public class PostsServlet extends HttpServlet {

    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Получаем все посты из базы данных
            var posts = postService.findAll();

            // Передаем посты в JSP
            req.setAttribute("posts", posts);

            // Перенаправляем на JSP страницу
            req.getRequestDispatcher("/jsp/posts.jsp").forward(req, resp);

        } catch (Exception e) {
            // В случае ошибки показываем пустой список
            req.setAttribute("posts", java.util.Collections.emptyList());
            req.getRequestDispatcher("/jsp/posts.jsp").forward(req, resp);
        }
    }
}
