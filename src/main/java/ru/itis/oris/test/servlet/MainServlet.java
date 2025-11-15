package ru.itis.oris.test.servlet;


import ru.itis.oris.test.model.User;
import ru.itis.oris.test.service.BookService;
import ru.itis.oris.test.service.BorrowService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    private BookService bookService = new BookService();
    private BorrowService borrowService = new BorrowService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        req.setAttribute("books", bookService.getAllBooks());

        if (user != null && user.getRole() == User.Role.ADMIN) {
            req.setAttribute("borrowedBooks", borrowService.getAllBorrowedBooks());
            req.getRequestDispatcher("/WEB-INF/jsp/admin/dashboard.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
        }
    }
}