package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.model.Product;
import ru.itis.oris.model.User;
import ru.itis.oris.service.OrderService;
import ru.itis.oris.service.ProductService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private ProductService productService = new ProductService();
    private OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Проверка прав администратора
        if (user == null || user.getRole() != User.Role.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Получаем статистику для админ-панели
        Object[] orderStats = orderService.getTotalRevenue() != null ?
                new Object[]{orderService.findAllOrders().size(), orderService.getTotalRevenue()} :
                new Object[]{0, BigDecimal.ZERO};

        List<Product> products = productService.getAllProducts();

        request.setAttribute("orderStats", orderStats);
        request.setAttribute("products", products);
        request.setAttribute("user", user);

        request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRole() != User.Role.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");

        if ("createProduct".equals(action)) {
            createProduct(request, response);
        } else if ("updateProduct".equals(action)) {
            updateProduct(request, response);
        } else if ("deleteProduct".equals(action)) {
            deleteProduct(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/admin?error=invalid_action");
        }
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String name = request.getParameter("name");
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            String category = request.getParameter("category");

            boolean success = productService.createProduct(name, price, category);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/admin?success=product_created");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin?error=create_failed");
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin?error=invalid_data");
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            Long productId = Long.parseLong(request.getParameter("productId"));
            String name = request.getParameter("name");
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            String category = request.getParameter("category");

            boolean success = productService.updateProduct(productId, name, price, category);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/admin?success=product_updated");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin?error=update_failed");
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin?error=invalid_data");
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            Long productId = Long.parseLong(request.getParameter("productId"));

            boolean success = productService.deleteProduct(productId);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/admin?success=product_deleted");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin?error=delete_failed");
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin?error=invalid_data");
        }
    }
}