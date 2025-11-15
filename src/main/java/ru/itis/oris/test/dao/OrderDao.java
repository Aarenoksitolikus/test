package ru.itis.oris.test.dao;

import ru.itis.oris.test.entities.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private final Connection connection;

    public OrderDao(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void create(Order order) throws SQLException {
        String sql = "INSERT INTO orders (user_id, total_price, status) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getUser_id());
            stmt.setDouble(2, order.getTotal_price());
            stmt.setBoolean(3, order.isStatus());
            stmt.executeUpdate();
        }
    }

    // READ (get by id)
    public Order findById(int id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUser_id(rs.getInt("user_id"));
                order.setTotal_price(rs.getDouble("total_price"));
                order.setStatus(rs.getBoolean("status"));
                return order;
            }
        }
        return null;
    }

    // READ ALL
    public List<Order> findAll() throws SQLException {
        String sql = "SELECT * FROM orders";
        List<Order> list = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUser_id(rs.getInt("user_id"));
                order.setTotal_price(rs.getDouble("total_price"));
                order.setStatus(rs.getBoolean("status"));
                list.add(order);
            }
        }
        return list;
    }

    // UPDATE
    public void update(Order order) throws SQLException {
        String sql = "UPDATE orders SET user_id = ?, total_price = ?, status = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getUser_id());
            stmt.setDouble(2, order.getTotal_price());
            stmt.setBoolean(3, order.isStatus());
            stmt.setInt(4, order.getId());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
