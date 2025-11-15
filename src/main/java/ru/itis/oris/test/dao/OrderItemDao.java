package ru.itis.oris.test.dao;

import ru.itis.oris.test.entities.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao {

    private final Connection connection;

    public OrderItemDao(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void create(OrderItem item) throws SQLException {
        String sql = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, item.getOrder_id());
            stmt.setInt(2, item.getProduct_id());
            stmt.setInt(3, item.getQuantity());
            stmt.executeUpdate();
        }
    }

    // READ (by id)
    public OrderItem findById(int id) throws SQLException {
        String sql = "SELECT * FROM order_items WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                OrderItem item = new OrderItem();
                item.setId(rs.getInt("id"));
                item.setOrder_id(rs.getInt("order_id"));
                item.setProduct_id(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                return item;
            }
        }
        return null;
    }

    // READ ALL
    public List<OrderItem> findAll() throws SQLException {
        List<OrderItem> list = new ArrayList<>();
        String sql = "SELECT * FROM order_items";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setId(rs.getInt("id"));
                item.setOrder_id(rs.getInt("order_id"));
                item.setProduct_id(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                list.add(item);
            }
        }
        return list;
    }

    // UPDATE
    public void update(OrderItem item) throws SQLException {
        String sql = "UPDATE order_items SET order_id = ?, product_id = ?, quantity = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, item.getOrder_id());
            stmt.setInt(2, item.getProduct_id());
            stmt.setInt(3, item.getQuantity());
            stmt.setInt(4, item.getId());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM order_items WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

