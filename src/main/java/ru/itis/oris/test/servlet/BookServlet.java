package ru.itis.oris.test.servlet;



import ru.itis.oris.test.model.User;
import ru.itis.oris.test.service.BookService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/books/*")
public class BookServlet extends HttpServlet {
    private BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null || user.getRole() != User.Role.ADMIN) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String action = req.getPathInfo();

        switch (action == null ? "" : action) {
            case "/create":
                req.getRequestDispatcher("/WEB-INF/jsp/admin/book-form.jsp").forward(req, resp);
                break;
            case "/edit":
                String idParam = req.getParameter("id");
                if (idParam != null) {
                    try {
                        Integer id = Integer.parseInt(idParam);
                        var book = bookService.getBookById(id);
                        if (book != null) {
                            req.setAttribute("book", book);
                            req.getRequestDispatcher("/WEB-INF/jsp/admin/book-form.jsp").forward(req, resp);
                        } else {
                            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                        }
                    } catch (NumberFormatException e) {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    }
                } else {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null || user.getRole() != User.Role.ADMIN) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String action = req.getPathInfo();

        switch (action == null ? "" : action) {
            case "/create":
                createBook(req, resp);
                break;
            case "/edit":
                updateBook(req, resp);
                break;
            case "/delete":
                deleteBook(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void createBook(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String title = req.getParameter("title");
        String author = req.getParameter("author");

        if (!bookService.validateBookData(title, author)) {
            req.setAttribute("error", "Все поля должны быть заполнены");
            req.getRequestDispatcher("/WEB-INF/jsp/admin/book-form.jsp").forward(req, resp);
            return;
        }

        bookService.createBook(title, author);
        resp.sendRedirect(req.getContextPath() + "/admin?tab=books");
    }

    private void updateBook(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String idParam = req.getParameter("id");
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String available = req.getParameter("available");

        if (idParam == null || !bookService.validateBookData(title, author)) {
            req.setAttribute("error", "Все поля должны быть заполнены");
            req.getRequestDispatcher("/WEB-INF/jsp/admin/book-form.jsp").forward(req, resp);
            return;
        }

        try {
            Integer id = Integer.parseInt(idParam);
            Boolean isAvailable = "on".equals(available);
            bookService.updateBook(id, title, author, isAvailable);
            resp.sendRedirect(req.getContextPath() + "/admin?tab=books");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void deleteBook(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            try {
                Integer id = Integer.parseInt(idParam);
                bookService.deleteBook(id);
                resp.sendRedirect(req.getContextPath() + "/admin?tab=books");
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}