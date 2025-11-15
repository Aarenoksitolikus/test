package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.model.Product;
import ru.itis.oris.model.User;
import ru.itis.oris.service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet("")
public class IndexServlet extends HttpServlet {
    private ProductService productService = new ProductService();

    @Override
    public void init() throws ServletException {
        // Инициализируем тестовые данные при запуске приложения
        productService.initializeTestProducts();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Получаем параметр категории для фильтрации
        String category = request.getParameter("category");
        List<Product> products;

        // Фильтруем продукты по категории или получаем все
        if (category != null && !category.isEmpty()) {
            products = productService.getProductsByCategory(category);
        } else {
            products = productService.getAllProducts();
        }

        // Проверяем авторизацию пользователя
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Устанавливаем атрибуты для JSP
        request.setAttribute("products", products);
        request.setAttribute("currentCategory", category);
        request.setAttribute("user", user);

        // Передаем управление на JSP страницу
        request.getRequestDispatcher("/views/index.jsp").forward(request, response);
    }
}