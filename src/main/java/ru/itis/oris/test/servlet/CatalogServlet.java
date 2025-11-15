package ru.itis.oris.test.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"ru\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Библиотека</title>");
        out.println("<link rel=\"stylesheet\" href=\"/src/main/webapp/css/main.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<header>");
        out.println("<nav>");
        out.println("<ul class=\"nav-links\">");
        out.println("<li><a href=\"/index\"> Главная </a></li>");
        out.println("<li><a href=\"/catalog\"> Каталог </a></li>");
        out.println("<li><a href=\"/booking\"> Ваши книги </a></li>");
        out.println("<li><a href=\"/booking\"> Ваш аккаунт </a></li>");
        out.println("</ul>");
        out.println("</nav>");
        out.println("</header>");

        out.println("<h1 id=\"h1_catalog\"> Каталог книг </h1>");
        out.println("<div class=\"catalog_section\">");



        out.println("<div class=\"book_container\">");
        List<Book> books = null;

        if (books != null) {
            for (Book book : books) {
                out.println("<div class=\"book_card\">");
                out.println("<p>" + book.getTitle() + "</p>");
                out.println("<p>" + book.getAuthor() + "</p>");
                out.println("<form action=\"/booking-book\" method=\"post\">");
                out.println("<input type=\"hidden\" name=\"id\" value=\"" + book.getId() + "\">");
                out.println("<input type=\"hidden\" name=\"name\" value=\"" + book.getTitle() + "\">");
                out.println("<input type=\"hidden\" name=\"price\" value=\"" + book.getAuthor() + "\">");
                out.println("<input type=\"hidden\" name=\"returnUrl\" value=\"/catalog\">");
                out.println("<button type=\"submit\" class=\"booking_button\"> Добавить </button>");
                out.println("</form>");
            }
        }

        out.println("</div>");
        out.println("<footer>");
        out.println("<p><b>Author:</b> Ivanov Maxim</p>");
        out.println("<p><a href="https://www.t.me/Aarenoksitolikus">Contact me</a></p>");
        out.println("</footer>");
        out.println("</body>");
        out.println("</html>");
    }
}
