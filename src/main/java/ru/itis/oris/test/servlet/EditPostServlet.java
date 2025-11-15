package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.test.model.entities.Post;
import ru.itis.oris.test.service.PostRepository;

import java.io.IOException;

@WebServlet("/edit")
public class EditPostServlet extends HttpServlet {

    private final PostRepository repo = PostRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");
        if (idParam == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
            return;
        }

        long id;
        try {
            id = Long.parseLong(idParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid id");
            return;
        }

        Post post = repo.find(id);
        if (post == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Post not found");
            return;
        }

        req.setAttribute("post", post);
        req.getRequestDispatcher("/WEB-INF/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        long id = Long.parseLong(req.getParameter("id"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        Post post = repo.find(id);
        if (post == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Post not found");
            return;
        }

        post.setTitle(title);
        post.setContent(content);  // ← завершено

        repo.update(post);

        resp.sendRedirect(req.getContextPath() + "/post?id=" + id);
    }
}
