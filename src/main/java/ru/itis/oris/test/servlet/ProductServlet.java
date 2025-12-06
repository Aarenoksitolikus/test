package ru.itis.oris.test.servlet;

import service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/menu")
public class ProductServlet extends HttpServlet {
    private final ProductService productService = new ProductService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String category = request.getParameter("category");
        
        if (category != null && !category.isEmpty()) {
            request.setAttribute("products", productService.getProductsByCategory(category));
        } else {
            request.setAttribute("products", productService.getAllProducts());
        }
        
        request.setAttribute("categories", productService.getAllCategories());
        request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp").forward(request, response);
    }
}