package ru.itis.oris.test.servlet;

import com.slavikjunior.deorm.orm.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.test.model.Comment;
import ru.itis.oris.test.model.Post;
import ru.itis.oris.test.service.CommentService;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/comment", "/comment/*"})
public class CommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postIdParam = req.getParameter("postId");
        if (postIdParam == null) {
            resp.sendRedirect(req.getContextPath() + "/posts");
            return;
        }

        try {
            int postId = Integer.parseInt(postIdParam);
            Post post = EntityManager.INSTANCE.getUnique(Post.class, java.util.Map.of("id", postId));
            if (post == null) {
                resp.sendRedirect(req.getContextPath() + "/posts");
                return;
            }

            List<Comment> comments = CommentService.getCommentsByPostId(postId);

            req.setAttribute("post", post);
            req.setAttribute("comments", comments);
            req.getRequestDispatcher("/jsp/comments.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/posts");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            resp.sendRedirect(req.getContextPath() + "/auth");
            return;
        }

        String action = req.getParameter("action");
        if ("add".equals(action)) {
            addComment(req, resp, session);
        } else if ("delete".equals(action)) {
            deleteComment(req, resp, session);
        } else {
            resp.sendRedirect(req.getContextPath() + "/posts");
        }
    }

    private void addComment(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
        String postIdParam = req.getParameter("postId");
        String content = req.getParameter("content");
        Integer userId = (Integer) session.getAttribute("user_id");

        if (postIdParam == null || content == null || userId == null || content.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/comment?postId=" + postIdParam + "&error=missing");
            return;
        }

        try {
            int postId = Integer.parseInt(postIdParam);
            CommentService.createComment(postId, userId, content);
            resp.sendRedirect(req.getContextPath() + "/comment?postId=" + postId);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/posts");
        }
    }

    private void deleteComment(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
        String commentIdParam = req.getParameter("commentId");
        String postIdParam = req.getParameter("postId");
        Integer userId = (Integer) session.getAttribute("user_id");
        String userRole = (String) session.getAttribute("role");

        if (commentIdParam == null || userId == null) {
            resp.sendRedirect(req.getContextPath() + "/posts");
            return;
        }

        try {
            int commentId = Integer.parseInt(commentIdParam);

            if (CommentService.canDeleteComment(commentId, userId, userRole)) {
                CommentService.deleteComment(commentId);
            }

            if (postIdParam != null) {
                resp.sendRedirect(req.getContextPath() + "/comment?postId=" + postIdParam);
            } else {
                resp.sendRedirect(req.getContextPath() + "/posts");
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/posts");
        }
    }
}