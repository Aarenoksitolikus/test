package ru.itis.oris.test.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.oris.test.dao.PostDao;
import ru.itis.oris.test.dao.CommentDao;
import ru.itis.oris.test.model.Post;
import ru.itis.oris.test.model.User;
import java.io.IOException;

@WebServlet("/posts/*")
public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            PostDao postDao = new PostDao();
            CommentDao commentDao = new CommentDao();

            if (path == null || path.equals("/")) {
                // Список постов
                request.setAttribute("posts", postDao.findAll());
                request.getRequestDispatcher("/jsp/posts.jsp").forward(request, response);
            } else if (path.equals("/create")) {
                // Форма создания
                request.getRequestDispatcher("/jsp/create-post.jsp").forward(request, response);
            } else if (path.startsWith("/view/")) {
                // Просмотр поста
                int postId = Integer.parseInt(path.substring(6));
                request.setAttribute("post", postDao.findById(postId));
                request.setAttribute("comments", commentDao.findByPostId(postId));
                request.getRequestDispatcher("/jsp/view-post.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            User user = (User) request.getSession().getAttribute("user");
            PostDao postDao = new PostDao();

            if (path.equals("/create")) {
                Post po = new Post();
                po.setTitle(request.getParameter("title"));
                po.setContent(request.getParameter("content"));
                po.setAuthorId(user.getId());
                postDao.save(po);
                response.sendRedirect("index");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
