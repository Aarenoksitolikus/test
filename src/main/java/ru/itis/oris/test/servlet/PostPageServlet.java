package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.test.model.Post;
import ru.itis.oris.test.util.ServiceProvider;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/post")
public class PostPageServlet extends HttpServlet {

    private PostService postService;

    @Override
    public void init() throws ServletException {
        postService = ServiceProvider.getInstance().getPostService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");
        if (idParam == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Post id is required");
            return;
        }

        Long id;
        try {
            id = Long.parseLong(idParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid post id format");
            return;
        }

        Optional<Post> postOpt = postService.getPostById(id);

        if (postOpt.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Post not found");
            return;
        }

        req.setAttribute("post", postOpt.get());

        req.getRequestDispatcher("/jsp/post.jsp")
                .forward(req, resp);
    }
}