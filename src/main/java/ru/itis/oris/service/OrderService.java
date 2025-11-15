package ru.itis.oris.service;

import ru.itis.oris.model.Order;
import ru.itis.oris.model.OrderItem;
import ru.itis.oris.model.Product;
import ru.itis.oris.repository.OrderRepository;
import ru.itis.oris.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderService {
    private OrderRepository orderRepository = new OrderRepository();
    private ProductRepository productRepository = new ProductRepository();

    public boolean createOrder(Long userId, Map<String, String[]> parameters) {
        try {
            Order order = new Order();
            order.setUserId(userId);
            order.setStatus(Order.Status.PENDING);

            // Парсим параметры для получения items
            String[] productIds = parameters.get("productId");
            String[] quantities = parameters.get("quantity");

            if (productIds != null && quantities != null && productIds.length == quantities.length) {
                for (int i = 0; i < productIds.length; i++) {
                    Long productId = Long.parseLong(productIds[i]);
                    Integer quantity = Integer.parseInt(quantities[i]);

                    if (quantity > 0) {
                        // Проверяем существование продукта
                        var productOpt = productRepository.findById(productId);
                        if (productOpt.isPresent()) {
                            OrderItem item = new OrderItem();
                            item.setProductId(productId);
                            item.setQuantity(quantity);
                            item.setProduct(productOpt.get());
                            order.addItem(item);
                        }
                    }
                }
            }

            // Рассчитываем общую сумму
            order.calculateTotalPrice();

            // Сохраняем заказ
            Long orderId = orderRepository.save(order);
            return orderId != null;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createOrderFromCart(Long userId, List<OrderItem> cartItems) {
        try {
            Order order = new Order();
            order.setUserId(userId);
            order.setStatus(Order.Status.PENDING);

            for (OrderItem cartItem : cartItems) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(cartItem.getProductId());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setProduct(cartItem.getProduct());
                order.addItem(orderItem);
            }

            // Рассчитываем общую сумму
            order.calculateTotalPrice();

            // Сохраняем заказ
            Long orderId = orderRepository.save(order);
            return orderId != null;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Order> findUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public boolean updateOrderStatus(Long orderId, Order.Status status) {
        return orderRepository.updateStatus(orderId, status);
    }

    public Order findById(Long orderId) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    // Получить общую выручку
    public BigDecimal getTotalRevenue() {
        return orderRepository.findAll().stream()
                .filter(order -> order.getStatus() == Order.Status.COMPLETED)
                .map(Order::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}