package ru.itis.oris.service;

import ru.itis.oris.model.Product;
import ru.itis.oris.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProductService {
    private ProductRepository productRepository = new ProductRepository();

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public boolean createProduct(String name, BigDecimal price, String category) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);

        return productRepository.save(product);
    }

    public boolean updateProduct(Long id, String name, BigDecimal price, String category) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setName(name);
            product.setPrice(price);
            product.setCategory(category);

            return productRepository.update(product);
        }
        return false;
    }

    public boolean deleteProduct(Long id) {
        return productRepository.delete(id);
    }

    public void initializeTestProducts() {
        if (getAllProducts().isEmpty()) {
            createProduct("Эспрессо", new BigDecimal("150.00"), "COFFEE");
            createProduct("Капучино", new BigDecimal("200.00"), "COFFEE");
            createProduct("Латте", new BigDecimal("220.00"), "COFFEE");
            createProduct("Американо", new BigDecimal("170.00"), "COFFEE");
            createProduct("Чай черный", new BigDecimal("100.00"), "TEA");
            createProduct("Чай зеленый", new BigDecimal("100.00"), "TEA");
            createProduct("Чизкейк", new BigDecimal("250.00"), "DESSERT");
            createProduct("Тирамису", new BigDecimal("280.00"), "DESSERT");
            createProduct("Сэндвич", new BigDecimal("300.00"), "FOOD");
            createProduct("Салат Цезарь", new BigDecimal("270.00"), "FOOD");
        }
    }
}
