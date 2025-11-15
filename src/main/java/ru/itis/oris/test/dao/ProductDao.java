package ru.itis.oris.test.dao;

import ru.itis.oris.test.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private final Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void create(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, price, category) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getPrice());
            stmt.setString(3, product.getCategory());
            stmt.executeUpdate();
        }
    }

    // READ by id
    public Product findById(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setCategory(rs.getString("category"));
                return product;
            }
        }
        return null;
    }

    public Product findByCategory(String category) throws SQLException {
        return null;
    }

    // READ ALL
    public List<Product> findAll() throws SQLException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setCategory(rs.getString("category"));
                list.add(product);
            }
        }
        return list;
    }

    // UPDATE
    public void update(Product product) throws SQLException {
        String sql = "UPDATE products SET name = ?, price = ?, category = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getPrice());
            stmt.setString(3, product.getCategory());
            stmt.setInt(4, product.getId());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

