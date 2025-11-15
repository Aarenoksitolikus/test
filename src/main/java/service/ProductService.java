package service;

import dao.ProductDao;
import model.Product;

import java.util.List;

public class ProductService {
    private final ProductDao productDao = new ProductDao();

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return productDao.findByCategory(category);
    }

    public Product getProductById(Integer id) {
        return productDao.findById(id);
    }

    public List<String> getAllCategories() {
        return productDao.findAllCategories();
    }

    public void createProduct(Product product) {
        productDao.save(product);
    }

    public void updateProduct(Product product) {
        productDao.update(product);
    }

    public void deleteProduct(Integer id) {
        productDao.delete(id);
    }
}