package ru.itis.oris.test.service;


import dao.OrderItemDao;
import entities.OrderItem;
import ru.itis.oris.test.dao.OrderItemDAO;

import java.util.List;

public class OrderItemService {

    private final OrderItemDAO orderItemDao = new OrderItemDAO();

    public List<OrderItem> getItemsByOrder(int orderId) {
        return orderItemDao.findByOrderId(orderId);
    }

    public boolean addItem(OrderItem item) {
        return orderItemDao.save(item);
    }

    public boolean deleteItem(int id) {
        return orderItemDao.delete(id);
    }
}
