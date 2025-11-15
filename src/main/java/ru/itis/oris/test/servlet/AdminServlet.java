package ru.itis.oris.test.servlet;


import ru.itis.oris.test.model.User;
import ru.itis.oris.test.service.BookService;
import ru.itis.oris.test.service.BorrowService;
import ru.itis.oris.test.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private BookService bookService = new BookService();
    private UserDao userDao = new UserDao();
    private BorrowService borrowService = new BorrowService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null || user.getRole() != User.Role.ADMIN) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String tab = req.getParameter("tab");
        if ("books".equals(tab)) {
            req.setAttribute("books", bookService.getAllBooks());
            req.getRequestDispatcher("/WEB-INF/jsp/admin/books.jsp").forward(req, resp);
        } else if ("users".equals(tab)) {
            req.setAttribute("users", userDao.findAll());
            req.setAttribute("borrowedBooks", borrowService.getAllBorrowedBooks());
            req.getRequestDispatcher("/WEB-INF/jsp/admin/users.jsp").forward(req, resp);
        } else {
            req.setAttribute("books", bookService.getAllBooks());
            req.getRequestDispatcher("/WEB-INF/jsp/admin/dashboard.jsp").forward(req, resp);
        }
    }
}
