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
import java.util.List;

@WebServlet(urlPatterns = {"/posts", "/posts/*"})
public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> posts = EntityManager.INSTANCE.get(Post.class);
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/jsp/posts.jsp").forward(req, resp);
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

        if (title == null || content == null || authorId == null) {
            resp.sendRedirect(req.getContextPath() + "/posts?error=missing");
            return;
        }

        Post post = new Post(0, title, authorId, content, new Timestamp(System.currentTimeMillis()));
        EntityManager.INSTANCE.create(post);

        resp.sendRedirect(req.getContextPath() + "/posts");
    }
}