package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.test.model.BookDao;
import ru.itis.oris.test.model.BorrowRecordDao;
import ru.itis.oris.test.model.User;

import java.io.IOException;

@WebServlet("/borrow")
public class BorrowServlet extends HttpServlet {
    private final BorrowRecordDao borrowRecordDao = new BorrowRecordDao();
    private final BookDao bookDao = new BookDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String bookIdStr = req.getParameter("bookId");
        try {
            Integer bookId = Integer.parseInt(bookIdStr);
            borrowRecordDao.borrowBook(bookId, user.getId());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/books");
    }
}