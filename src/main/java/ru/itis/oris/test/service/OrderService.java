package ru.itis.oris.test.service;
import ru.itis.oris.test.db.ConnectionFactory;
import ru.itis.oris.test.entities.Order;

import dao.OrderDao;
import dao.OrderItemDao;
import ru.itis.oris.test.entities.OrderItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderService {

    private final OrderDao orderDao;

    {
        try {
            orderDao = new OrderDao(ConnectionFactory.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final OrderItemDao orderItemDao;

    {
        try {
            orderItemDao = new OrderItemDao(ConnectionFactory.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Order getById(int id) {
        return orderDao.findById(id);
    }

    public List<Order> getOrdersByUser(int userId) {
        return orderDao.findByUserId(userId);
    }

    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    public int createOrder(Order order, List<OrderItem> items) {
        int orderId = orderDao.create(order);

        for (OrderItem item : items) {
            item.setOrderId(orderId);
            orderItemDao.save(item);
        }

        return orderId;
    }

    public boolean updateStatus(int orderId, String status) {
        return orderDao.updateStatus(orderId, status);
    }
}
