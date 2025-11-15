package ru.itis.oris.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private Long userId;
    private BigDecimal totalPrice;
    private Status status;
    private LocalDateTime createdAt;
    private List<OrderItem> items = new ArrayList<>();

    public enum Status {
        PENDING, CONFIRMED, COMPLETED, CANCELLED
    }

    public Order() {}

    public Order(Long id, Long userId, BigDecimal totalPrice, Status status, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    // Методы
    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    public void calculateTotalPrice() {
        if (items != null && !items.isEmpty()) {
            this.totalPrice = BigDecimal.ZERO;
            for (OrderItem item : items) {
                if (item.getProduct() != null && item.getQuantity() != null) {
                    BigDecimal itemTotal = item.getProduct().getPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity()));
                    this.totalPrice = this.totalPrice.add(itemTotal);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Order{id=" + id + ", userId=" + userId + ", totalPrice=" + totalPrice +
                ", status=" + status + ", items=" + items.size() + "}";
    }
}