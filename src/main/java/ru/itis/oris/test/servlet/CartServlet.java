package ru.itis.oris.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.model.OrderItem;
import ru.itis.oris.model.Product;
import ru.itis.oris.model.User;
import ru.itis.oris.service.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Показываем страницу корзины
        request.getRequestDispatcher("/views/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Добавление товара в корзину
            addToCart(request, response, session);
        } else if ("update".equals(action)) {
            // Обновление количества
            updateCartItem(request, response, session);
        } else if ("remove".equals(action)) {
            // Удаление товара из корзины
            removeFromCart(request, response, session);
        } else if ("clear".equals(action)) {
            // Очистка корзины
            clearCart(session);
            response.sendRedirect(request.getContextPath() + "/cart");
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws IOException {
        Long productId = Long.parseLong(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Optional<Product> productOpt = productService.getProductById(productId);
        if (productOpt.isPresent()) {
            // Получаем или создаем корзину в сессии
            List<OrderItem> cart = getOrCreateCart(session);

            // Проверяем, есть ли уже этот товар в корзине
            boolean found = false;
            for (OrderItem item : cart) {
                if (item.getProductId().equals(productId)) {
                    item.setQuantity(item.getQuantity() + quantity);
                    found = true;
                    break;
                }
            }

            // Если товара нет в корзине - добавляем
            if (!found) {
                OrderItem newItem = new OrderItem();
                newItem.setProductId(productId);
                newItem.setQuantity(quantity);
                newItem.setProduct(productOpt.get());
                cart.add(newItem);
            }

            session.setAttribute("cart", cart);
            response.sendRedirect(request.getContextPath() + "/?added=true");
        } else {
            response.sendRedirect(request.getContextPath() + "/?error=product_not_found");
        }
    }

    private void updateCartItem(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws IOException {
        Long productId = Long.parseLong(request.getParameter("productId"));
        int newQuantity = Integer.parseInt(request.getParameter("quantity"));

        List<OrderItem> cart = getOrCreateCart(session);

        for (OrderItem item : cart) {
            if (item.getProductId().equals(productId)) {
                if (newQuantity <= 0) {
                    cart.remove(item);
                } else {
                    item.setQuantity(newQuantity);
                }
                break;
            }
        }

        session.setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath() + "/cart");
    }

    private void removeFromCart(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws IOException {
        Long productId = Long.parseLong(request.getParameter("productId"));

        List<OrderItem> cart = getOrCreateCart(session);
        cart.removeIf(item -> item.getProductId().equals(productId));

        session.setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath() + "/cart");
    }

    private void clearCart(HttpSession session) {
        session.removeAttribute("cart");
    }

    @SuppressWarnings("unchecked")
    private List<OrderItem> getOrCreateCart(HttpSession session) {
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}
