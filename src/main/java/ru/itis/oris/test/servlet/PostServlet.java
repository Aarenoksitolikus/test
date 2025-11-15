package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.test.model.UserEntity;
import ru.itis.oris.test.service.PostService;

import java.io.IOException;

@WebServlet("/posts")
public class PostServlet extends HttpServlet {
    private PostService postService;

    @Override
    public void init() {
        postService = new PostService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("new".equals(action)) {
            req.getRequestDispatcher("").forward(req, resp);
        } else if ("edit".equals(action)) {
            String id = req.getParameter("id");
            if (id != null) {
                var post = postService.getPostById(Long.parseLong(id));
                req.setAttribute("post", post);
                req.getRequestDispatcher("").forward(req, resp);
            }
        } else {
            var posts = postService.getAllPosts();
            req.setAttribute("posts", posts);
            req.getRequestDispatcher("").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("/login");
            return;
        }


    }
}