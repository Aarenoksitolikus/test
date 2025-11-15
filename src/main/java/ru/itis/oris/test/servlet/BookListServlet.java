package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.test.model.Book;
import ru.itis.oris.test.model.User;
import ru.itis.oris.test.service.BookService;

import java.io.IOException;
import java.util.List;

@WebServlet("/booklist")
public class BookListServlet extends HttpServlet {

    private BookService bookService;

    @Override
    public void init() throws ServletException {
        this.bookService = (BookService) getServletContext().getAttribute("bookService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Book> bookList = bookService.getAllByUserId(user.getId());
        req.setAttribute("books", bookList);
        req.getRequestDispatcher("/jsp/booklist.jsp").forward(req, resp);
    }
}
