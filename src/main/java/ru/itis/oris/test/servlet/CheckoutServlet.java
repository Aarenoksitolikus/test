package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.model.OrderItem;
import ru.itis.oris.model.User;
import ru.itis.oris.service.OrderService;

import java.io.IOException;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private OrderService orderService = new OrderService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        @SuppressWarnings("unchecked")
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart?error=empty_cart");
            return;
        }

        // Создаем заказ из корзины
        boolean success = orderService.createOrderFromCart(user.getId(), cart);

        if (success) {
            // Очищаем корзину после успешного заказа
            session.removeAttribute("cart");
            response.sendRedirect(request.getContextPath() + "/orders?success=true");
        } else {
            response.sendRedirect(request.getContextPath() + "/cart?error=order_failed");
        }
    }
}
