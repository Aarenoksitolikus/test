package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.model.Order;
import ru.itis.oris.model.User;
import ru.itis.oris.service.OrderService;

import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
    private OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<Order> orders;
        if (user.getRole() == User.Role.ADMIN) {
            // Админ видит все заказы
            orders = orderService.findAllOrders();
            request.setAttribute("isAdmin", true);
        } else {
            // Пользователь видит только свои заказы
            orders = orderService.findUserOrders(user.getId());
            request.setAttribute("isAdmin", false);
        }

        request.setAttribute("orders", orders);
        request.setAttribute("user", user);

        // Сообщения об успехе/ошибке
        if (request.getParameter("success") != null) {
            request.setAttribute("successMessage", "Заказ успешно создан!");
        }
        if (request.getParameter("updated") != null) {
            request.setAttribute("successMessage", "Статус заказа обновлен!");
        }
        if (request.getParameter("error") != null) {
            request.setAttribute("errorMessage", "Произошла ошибка при создании заказа");
        }

        request.getRequestDispatcher("/views/orders.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");

        if ("updateStatus".equals(action) && user.getRole() == User.Role.ADMIN) {
            // Обновление статуса заказа (только для админа)
            updateOrderStatus(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/orders?error=invalid_action");
        }
    }

    private void updateOrderStatus(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            Long orderId = Long.parseLong(request.getParameter("orderId"));
            Order.Status newStatus = Order.Status.valueOf(request.getParameter("status"));

            boolean success = orderService.updateOrderStatus(orderId, newStatus);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/orders?updated=true");
            } else {
                response.sendRedirect(request.getContextPath() + "/orders?error=update_failed");
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/orders?error=invalid_data");
        }
    }
}
