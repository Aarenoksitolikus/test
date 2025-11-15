package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.test.dao.OrderDao;
import ru.itis.oris.test.dao.OrderItemDao;
import ru.itis.oris.test.db.ConnectionFactory;
import ru.itis.oris.test.entities.Order;
import ru.itis.oris.test.entities.User;
import ru.itis.oris.test.service.OrderService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        try {
            orderService = new OrderService(new OrderDao(ConnectionFactory.getConnection()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            try {
                resp.sendRedirect(req.getContextPath() + "/login");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        List<Order> orders;
        if(user.getRole().equals("ADMIN")) {
            orders = orderService.getAllOrders();
        } else {
            orders = orderService.getOrdersByUser(user.getId());
        }

        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(req, resp);
    }
}

