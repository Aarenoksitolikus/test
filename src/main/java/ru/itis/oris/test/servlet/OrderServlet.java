package ru.itis.oris.test.servlet;

import model.Order;
import model.OrderItem;
import model.User;
import service.OrderService;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/order/*")
public class OrderServlet extends HttpServlet {
    private final OrderService orderService = new OrderService();
    private final ProductService productService = new ProductService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String path = request.getPathInfo();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }
        
        if (path == null || "/my".equals(path)) {
            // View user's orders
            request.setAttribute("orders", orderService.getUserOrders(user.getId()));
            request.getRequestDispatcher("/WEB-INF/jsp/my-orders.jsp").forward(request, response);
        } else if ("/create".equals(path)) {
            // Show order creation form
            request.setAttribute("products", productService.getAllProducts());
            request.getRequestDispatcher("/WEB-INF/jsp/create-order.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String path = request.getPathInfo();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }
        
        if ("/create".equals(path)) {
            handleCreateOrder(request, response, user);
        }
    }
    
    private void handleCreateOrder(HttpServletRequest request, HttpServletResponse response, User user) 
            throws ServletException, IOException {
        String[] productIds = request.getParameterValues("productId");
        String[] quantities = request.getParameterValues("quantity");
        
        if (productIds == null || quantities == null) {
            request.setAttribute("error", "Выберите хотя бы один продукт");
            request.setAttribute("products", productService.getAllProducts());
            request.getRequestDispatcher("/WEB-INF/jsp/create-order.jsp").forward(request, response);
            return;
        }
        
        List<OrderItem> items = new ArrayList<>();
        for (int i = 0; i < productIds.length; i++) {
            int productId = Integer.parseInt(productIds[i]);
            int quantity = Integer.parseInt(quantities[i]);
            
            if (quantity > 0) {
                OrderItem item = new OrderItem();
                item.setProductId(productId);
                item.setQuantity(quantity);
                items.add(item);
            }
        }
        
        if (items.isEmpty()) {
            request.setAttribute("error", "Выберите хотя бы один продукт");
            request.setAttribute("products", productService.getAllProducts());
            request.getRequestDispatcher("/WEB-INF/jsp/create-order.jsp").forward(request, response);
            return;
        }
        
        Order order = new Order();
        order.setUserId(user.getId());
        order.setStatus("PENDING");
        order.setTotalPrice(BigDecimal.ZERO);
        
        orderService.createOrder(order, items);
        response.sendRedirect(request.getContextPath() + "/order/my");
    }
}