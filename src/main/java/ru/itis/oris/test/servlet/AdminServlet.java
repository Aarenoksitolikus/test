package ru.itis.oris.test.servlet;

import model.Product;
import service.OrderService;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final OrderService orderService = new OrderService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String path = request.getPathInfo();
        
        if (path == null || "/".equals(path)) {
            // Admin dashboard
            request.getRequestDispatcher("/WEB-INF/jsp/admin/dashboard.jsp").forward(request, response);
        } else if ("/products".equals(path)) {
            // Product management
            request.setAttribute("products", productService.getAllProducts());
            request.getRequestDispatcher("/WEB-INF/jsp/admin/products.jsp").forward(request, response);
        } else if ("/orders".equals(path)) {
            // Order management
            request.setAttribute("orders", orderService.getAllOrders());
            request.getRequestDispatcher("/WEB-INF/jsp/admin/orders.jsp").forward(request, response);
        } else if ("/products/edit".equals(path)) {
            // Edit product form
            String idParam = request.getParameter("id");
            if (idParam != null) {
                Product product = productService.getProductById(Integer.parseInt(idParam));
                request.setAttribute("product", product);
            }
            request.getRequestDispatcher("/WEB-INF/jsp/admin/edit-product.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String path = request.getPathInfo();
        
        if ("/products/create".equals(path)) {
            handleCreateProduct(request, response);
        } else if ("/products/update".equals(path)) {
            handleUpdateProduct(request, response);
        } else if ("/products/delete".equals(path)) {
            handleDeleteProduct(request, response);
        } else if ("/orders/update-status".equals(path)) {
            handleUpdateOrderStatus(request, response);
        }
    }
    
    private void handleCreateProduct(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String category = request.getParameter("category");
        
        // Validation
        if (name == null || name.trim().isEmpty() || 
            priceStr == null || priceStr.trim().isEmpty() || 
            category == null || category.trim().isEmpty()) {
            request.setAttribute("error", "Все поля обязательны для заполнения");
            request.getRequestDispatcher("/WEB-INF/jsp/admin/edit-product.jsp").forward(request, response);
            return;
        }
        
        try {
            BigDecimal price = new BigDecimal(priceStr);
            Product product = new Product(name, price, category);
            productService.createProduct(product);
            response.sendRedirect(request.getContextPath() + "/admin/products");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Неверный формат цены");
            request.getRequestDispatcher("/WEB-INF/jsp/admin/edit-product.jsp").forward(request, response);
        }
    }
    
    private void handleUpdateProduct(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String category = request.getParameter("category");

        // Validation
        if (idStr == null || name == null || name.trim().isEmpty() ||
                priceStr == null || priceStr.trim().isEmpty() ||
                category == null || category.trim().isEmpty()) {
            request.setAttribute("error", "Все поля обязательны для заполнения");
            request.getRequestDispatcher("/WEB-INF/jsp/admin/edit-product.jsp").forward(request, response);
            return;
        }

        try {
            Integer id = Integer.parseInt(idStr);
            BigDecimal price = new BigDecimal(priceStr);
            Product product = new Product(name, price, category);
            product.setId(id);
            productService.updateProduct(product);
            response.sendRedirect(request.getContextPath() + "/admin/products");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Неверный формат данных");
            request.getRequestDispatcher("/WEB-INF/jsp/admin/edit-product.jsp").forward(request, response);
        }
    }

    private void handleDeleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            productService.deleteProduct(Integer.parseInt(idStr));
        }
        response.sendRedirect(request.getContextPath() + "/admin/products");
    }

    private void handleUpdateOrderStatus(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String orderIdStr = request.getParameter("orderId");
        String status = request.getParameter("status");

        if (orderIdStr != null && status != null) {
            orderService.updateOrderStatus(Integer.parseInt(orderIdStr), status);
        }
        response.sendRedirect(request.getContextPath() + "/admin/orders");
    }
}