package ru.itis.oris.test.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItem {
    int id;
    int order_id;
    int product_id;
    int quantity;

    public OrderItem() {}
}
