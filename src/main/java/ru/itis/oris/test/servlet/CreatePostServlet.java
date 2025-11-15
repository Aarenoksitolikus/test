package ru.itis.oris.test.servlet;

import com.slavikjunior.deorm.orm.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.test.model.Post;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/create")
public class CreatePostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            resp.sendRedirect(req.getContextPath() + "/auth");
            return;
        }
        req.getRequestDispatcher("/jsp/create-post.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/auth");
            return;
        }

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Integer authorId = (Integer) session.getAttribute("user_id");

        if (title == null || content == null || authorId == null || title.isBlank() || content.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/create?error=missing");
            return;
        }

        try {
            Post post = new Post(0, title, authorId, content, new Timestamp(System.currentTimeMillis()));
            EntityManager.INSTANCE.create(post);
            resp.sendRedirect(req.getContextPath() + "/posts");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/create?error=server");
        }
    }
}