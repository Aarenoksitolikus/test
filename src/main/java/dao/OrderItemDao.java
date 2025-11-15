package dao;

import model.OrderItem;
import util.DatabaseConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao {

    public void save(OrderItem orderItem) {
        String sql = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, orderItem.getOrderId());
            statement.setInt(2, orderItem.getProductId());
            statement.setInt(3, orderItem.getQuantity());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderItem.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrderItem> findByOrderId(Integer orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT oi.*, p.name, p.price, p.category " +
                "FROM order_items oi " +
                "JOIN products p ON oi.product_id = p.id " +
                "WHERE oi.order_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderItem orderItem = mapResultSetToOrderItem(resultSet);
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    private OrderItem mapResultSetToOrderItem(ResultSet resultSet) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(resultSet.getInt("id"));
        orderItem.setOrderId(resultSet.getInt("order_id"));
        orderItem.setProductId(resultSet.getInt("product_id"));
        orderItem.setQuantity(resultSet.getInt("quantity"));

        // Create and set product
        ProductDao productDao = new ProductDao();
        orderItem.setProduct(productDao.findById(resultSet.getInt("product_id")));

        return orderItem;
    }
}
