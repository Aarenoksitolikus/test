package ru.itis.oris.test.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.itis.oris.test.repository.BookRepository;
import ru.itis.oris.test.repository.UserRepository;
import ru.itis.oris.test.service.BookService;
import ru.itis.oris.test.service.BorrowService;
import ru.itis.oris.test.service.UserService;

@WebListener
public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        context.setAttribute("userService", userService);

        BookRepository bookRepository = new BookRepository();
        BookService bookService = new BookService(bookRepository);
        context.setAttribute("bookService", bookService);

        BorrowService borrowService = new BorrowService(bookRepository);
    }
}
