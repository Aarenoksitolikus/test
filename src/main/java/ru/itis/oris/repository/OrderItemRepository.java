package ru.itis.oris.repository;

import ru.itis.oris.model.OrderItem;
import ru.itis.oris.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemRepository {

    // Сохранение одного элемента заказа
    public boolean save(OrderItem orderItem) {
        String sql = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, orderItem.getOrderId());
            stmt.setLong(2, orderItem.getProductId());
            stmt.setInt(3, orderItem.getQuantity());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Пакетное сохранение элементов заказа
    public boolean saveAll(List<OrderItem> orderItems) {
        String sql = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            for (OrderItem item : orderItems) {
                stmt.setLong(1, item.getOrderId());
                stmt.setLong(2, item.getProductId());
                stmt.setInt(3, item.getQuantity());
                stmt.addBatch();
            }

            int[] results = stmt.executeBatch();
            return results.length == orderItems.size();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Получение всех элементов для заказа
    public List<OrderItem> findByOrderId(Long orderId) {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT oi.*, p.name, p.price, p.category " +
                "FROM order_items oi " +
                "JOIN products p ON oi.product_id = p.id " +
                "WHERE oi.order_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderItem item = mapOrderItemFromResultSet(rs);
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    // Получение элемента по ID
    public OrderItem findById(Long id) {
        String sql = "SELECT oi.*, p.name, p.price, p.category " +
                "FROM order_items oi " +
                "JOIN products p ON oi.product_id = p.id " +
                "WHERE oi.id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapOrderItemFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Обновление количества товара в элементе заказа
    public boolean updateQuantity(Long orderItemId, Integer newQuantity) {
        String sql = "UPDATE order_items SET quantity = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, newQuantity);
            stmt.setLong(2, orderItemId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Удаление элемента заказа
    public boolean delete(Long orderItemId) {
        String sql = "DELETE FROM order_items WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, orderItemId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Удаление всех элементов заказа
    public boolean deleteByOrderId(Long orderId) {
        String sql = "DELETE FROM order_items WHERE order_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, orderId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Получение популярных товаров (для админ-панели)
    public List<Object[]> getPopularProducts(int limit) {
        List<Object[]> popularProducts = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.category, SUM(oi.quantity) as total_sold " +
                "FROM order_items oi " +
                "JOIN products p ON oi.product_id = p.id " +
                "JOIN orders o ON oi.order_id = o.id " +
                "WHERE o.status = 'COMPLETED' " +
                "GROUP BY p.id, p.name, p.category " +
                "ORDER BY total_sold DESC " +
                "LIMIT ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] productData = new Object[4];
                productData[0] = rs.getLong("id");
                productData[1] = rs.getString("name");
                productData[2] = rs.getString("category");
                productData[3] = rs.getInt("total_sold");
                popularProducts.add(productData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return popularProducts;
    }

    // Маппинг OrderItem из ResultSet
    private OrderItem mapOrderItemFromResultSet(ResultSet rs) throws SQLException {
        OrderItem item = new OrderItem();
        item.setId(rs.getLong("id"));
        item.setOrderId(rs.getLong("order_id"));
        item.setProductId(rs.getLong("product_id"));
        item.setQuantity(rs.getInt("quantity"));

        // Создаем и заполняем объект Product
        ru.itis.oris.model.Product product = new ru.itis.oris.model.Product();
        product.setId(rs.getLong("product_id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setCategory(rs.getString("category"));

        item.setProduct(product);
        return item;
    }
}
