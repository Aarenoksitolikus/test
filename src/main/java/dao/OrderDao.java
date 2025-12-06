package dao;

import util.DatabaseConnection;
import model.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    public void save(Order order) {
        String sql = "INSERT INTO orders (user_id, total_price, status) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, order.getUserId());
            statement.setBigDecimal(2, order.getTotalPrice());
            statement.setString(3, order.getStatus());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> findByUserId(Integer userId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY created_at DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                orders.add(mapResultSetToOrder(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY created_at DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                orders.add(mapResultSetToOrder(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Order findById(Integer id) {
        String sql = "SELECT * FROM orders WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToOrder(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateStatus(Integer orderId, String status) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Order mapResultSetToOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setUserId(resultSet.getInt("user_id"));
        order.setTotalPrice(resultSet.getBigDecimal("total_price"));
        order.setStatus(resultSet.getString("status"));
        order.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        return order;
    }
}
