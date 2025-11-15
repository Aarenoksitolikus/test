package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.test.model.Book;
import ru.itis.oris.test.service.BookService;
import ru.itis.oris.test.service.UserService;
import ru.itis.oris.test.util.BookDAO;
import ru.itis.oris.test.util.UserDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    private BookService bookService;
    private BookDAO bookDAO;
    private UserService userService;
    private UserDAO userDAO;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String username = UserService.checkUser(req);

        req.setAttribute("username",username);


        try {
            List<Book> list = bookService.getBooks();
            req.setAttribute("list", list);
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        req.getRequestDispatcher("/jsp/books.jsp").forward(req, resp);


    }

    @Override
    public void init() throws ServletException {
        this.bookService = new BookService();
        this.userService = new UserService();
    }
}
