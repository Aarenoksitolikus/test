package service;

import dao.OrderDao;
import dao.OrderItemDao;
import model.Order;
import model.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public class OrderService {
    private final OrderDao orderDao = new OrderDao();
    private final OrderItemDao orderItemDao = new OrderItemDao();
    private final ProductService productService = new ProductService();

    public void createOrder(Order order, List<OrderItem> items) {
        // Calculate total price
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItem item : items) {
            BigDecimal productPrice = productService.getProductById(item.getProductId()).getPrice();
            totalPrice = totalPrice.add(productPrice.multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        order.setTotalPrice(totalPrice);

        // Save order
        orderDao.save(order);

        // Save order items
        for (OrderItem item : items) {
            item.setOrderId(order.getId());
            orderItemDao.save(item);
        }
    }

    public List<Order> getUserOrders(Integer userId) {
        List<Order> orders = orderDao.findByUserId(userId);
        for (Order order : orders) {
            order.setItems(orderItemDao.findByOrderId(order.getId()));
        }
        return orders;
    }


    public List<Order> getAllOrders() {
        List<Order> orders = orderDao.findAll();
        for (Order order : orders) {
            order.setItems(orderItemDao.findByOrderId(order.getId()));
        }
        return orders;
    }

    public void updateOrderStatus(Integer orderId, String status) {
        orderDao.updateStatus(orderId, status);
    }

    public Order getOrderById(Integer id) {
        Order order = orderDao.findById(id);
        if (order != null) {
            order.setItems(orderItemDao.findByOrderId(order.getId()));
        }
        return order;
    }
}
