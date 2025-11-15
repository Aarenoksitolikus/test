package ru.itis.oris.test.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.oris.test.dao.CommentDao;
import ru.itis.oris.test.model.Comment;
import ru.itis.oris.test.model.User;
import java.io.IOException;

@WebServlet("/comments/*")
public class CommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            User user = (User) request.getSession().getAttribute("user");
            CommentDao commentDao = new CommentDao();

            if (path.equals("/add")) {
                Comment com = new Comment();
                com.setPostId(Integer.parseInt(request.getParameter("postId")));
                com.setUserId(user.getId());
                com.setContent(request.getParameter("content"));
                commentDao.save(com);
                response.sendRedirect("../posts/view/" + com.getPostId());
            } else if (path.startsWith("/delete/")) {
                int commentId = Integer.parseInt(path.substring(8));
                int postId = Integer.parseInt(request.getParameter("postId"));

                if (user.getRole().equals("MODERATOR")) {
                    commentDao.delete(commentId);
                }
                response.sendRedirect("../posts/view/" + postId);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
