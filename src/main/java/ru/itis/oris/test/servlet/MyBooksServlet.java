package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.test.model.BorrowRecordDao;
import ru.itis.oris.test.model.User;

import java.io.IOException;

@WebServlet("/my-books")
public class MyBooksServlet extends HttpServlet {
    private final BorrowRecordDao borrowRecordDao = new BorrowRecordDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        var borrowRecords = borrowRecordDao.findCurrentBorrowsByUser(user.getId());
        req.setAttribute("borrowRecords", borrowRecords);
        req.getRequestDispatcher("/jsp/my-books.jsp").forward(req, resp);
    }
}