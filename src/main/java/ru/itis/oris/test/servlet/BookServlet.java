package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.test.model.BookDao;

import java.io.IOException;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    private final BookDao bookDao = new BookDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var books = bookDao.findAll();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/jsp/books.jsp").forward(req, resp);
    }
}