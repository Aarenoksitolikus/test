package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.test.model.Post;
import ru.itis.oris.test.model.User;
import ru.itis.oris.test.service.PostService;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/posts/create")
public class CreatePostServlet extends HttpServlet {

    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Проверка авторизации
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.getRequestDispatcher("/jsp/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Проверка авторизации
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setCharacterEncoding("UTF-8");

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        User author = (User) session.getAttribute("user");

        // Валидация данных
        if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty()) {
            resp.sendRedirect("create?error=empty");
            return;
        }

        if (title.length() > 255) {
            resp.sendRedirect("create?error=title_too_long");
            return;
        }

        // Создание и сохранение поста
        Post post = new Post();
        post.setTitle(title.trim());
        post.setContent(content.trim());
        post.setAuthorId(author.getId());
        post.setCreatedDate(LocalDateTime.now());

        try {
            postService.save(post);
            resp.sendRedirect(req.getContextPath() + "/posts?success=created");
        } catch (Exception e) {
            resp.sendRedirect("create?error=server");
        }
    }
}
