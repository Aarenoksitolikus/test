package ru.itis.oris.test.servlet;

import ru.itis.oris.test.model.Product;
import ru.itis.oris.test.model.ProductDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    private ProductDao productDao = new ProductDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String category = request.getParameter("category");
        List<Product> products;

        if (category != null && !category.equals("all")) {
            products = productDao.findByCategory(category);
        } else {
            products = productDao.findAll();
        }

        request.setAttribute("products", products);
        request.getRequestDispatcher("/jsp/user/menu.jsp").forward(request, response);
    }
}
