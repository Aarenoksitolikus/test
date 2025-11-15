package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.oris.test.model.Book;
import ru.itis.oris.test.service.BookService;
import ru.itis.oris.test.util.BookDAO;

import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private BookService bookService;
    private BookDAO bookDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.getRequestDispatcher("/jsp/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String action = req.getParameter("action");

        String errorMessage = null;

        try {
            switch (action) {
                case "createBook":
                    if (title != null && action != null) {
                        Book book = new Book(title, author, true);

                        bookService.createBook(book);
                    } else {
                        errorMessage = "Поля не могут быть пустыми";
                    }
                    break;
                case "deleteBook":
                    Book book = new Book(title, author, true);

                    bookService.deleteBook(book);
                    break;

                default:
                    errorMessage = "Неизвестное действие";
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorMessage = "Ошибка при выполнении действия: " + e.getMessage();
        }

        req.setAttribute("errorMessage", errorMessage != null ? errorMessage : "");
        req.getRequestDispatcher("/jsp/admin.jsp").forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.bookService = new BookService();
    }
}
