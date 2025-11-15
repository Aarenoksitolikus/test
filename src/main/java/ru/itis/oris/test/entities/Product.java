package ru.itis.oris.test.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    int id;
    String name;
    int price;
    String category;

    public Product() {}
}
