package ru.itis.oris.test.servlet;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ru.itis.oris.test.model.PostDaoImpl;
import ru.itis.oris.test.model.dao.PostDao;
import ru.itis.oris.test.model.entities.Post;

import java.io.IOException;
import java.util.List;

@WebServlet({"/", "/posts"})
public class PostsListServlet extends HttpServlet {
    private final PostDao postDao = new PostDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> posts = postDao.findAll();
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/WEB-INF/jsp/posts.jsp").forward(req, resp);
    }
}
