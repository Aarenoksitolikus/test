package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.test.dao.OrderDao;
import ru.itis.oris.test.dao.OrderItemDao;
import ru.itis.oris.test.dao.ProductDao;
import ru.itis.oris.test.entities.Order;
import ru.itis.oris.test.entities.Product;
import ru.itis.oris.test.entities.User;
import ru.itis.oris.test.service.OrderService;

import java.io.IOException;

@WebServlet("/order/create")
public class CreateOrderServlet extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        orderService = new OrderService(new OrderDao(Database.getConnection()),
                new OrderItemDao(Database.getConnection()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null || !user.getRole().equals("USER")) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        // Получаем продукты и их количество из формы
        String[] productIds = req.getParameterValues("productId");
        String[] quantities = req.getParameterValues("quantity");

        Order order = new Order();
        order.setUser_id(user.getId());
        order.setStatus(false);
        double totalPrice = 0;

        for(int i = 0; i < productIds.length; i++){
            int productId = Integer.parseInt(productIds[i]);
            int quantity = Integer.parseInt(quantities[i]);
            Product product = new ProductDao(Database.getConnection()).findById(productId);
            totalPrice += product.getPrice() * quantity;
        }

        order.setTotal_price(totalPrice);
        orderService.createOrder(order, productIds, quantities);

        resp.sendRedirect(req.getContextPath() + "/orders");
    }
}

