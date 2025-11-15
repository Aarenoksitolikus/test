package ru.itis.oris.test.servlet;


import ru.itis.oris.test.model.User;
import ru.itis.oris.test.service.BorrowService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/borrow/*")
public class BorrowServlet extends HttpServlet {
    private BorrowService borrowService = new BorrowService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String action = req.getPathInfo();

        switch (action == null ? "" : action) {
            case "/take":
                borrowBook(req, resp, user);
                break;
            case "/return":
                returnBook(req, resp, user);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void borrowBook(HttpServletRequest req, HttpServletResponse resp, User user)
            throws IOException {
        String bookIdParam = req.getParameter("bookId");
        if (bookIdParam != null) {
            try {
                Integer bookId = Integer.parseInt(bookIdParam);
                if (borrowService.borrowBook(bookId, user.getId())) {
                    resp.sendRedirect(req.getContextPath() + "/?success=book_borrowed");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/?error=book_not_available");
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void returnBook(HttpServletRequest req, HttpServletResponse resp, User user)
            throws IOException {
        String recordIdParam = req.getParameter("recordId");
        if (recordIdParam != null) {
            try {
                Integer recordId = Integer.parseInt(recordIdParam);
                borrowService.returnBook(recordId);
                resp.sendRedirect(req.getContextPath() + "/profile?success=book_returned");
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}