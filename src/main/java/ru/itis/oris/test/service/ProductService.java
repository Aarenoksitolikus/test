package ru.itis.oris.test.service;


import ru.itis.oris.test.dao.ProductDao;
import ru.itis.oris.test.entities.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    private final ProductDao productDao = new ProductDao();

    public List<Product> getAll() {
        try {
            return productDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getByCategory(String category) {
        try {
            return productDao.findByCategory(category);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product getById(int id) {
        try {
            return productDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean create(Product product) {
        return productDao.save(product);
    }

    public boolean update(Product product) {
        try {
            return productDao.update(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) throws SQLException {
        return productDao.delete(id);
    }
}



