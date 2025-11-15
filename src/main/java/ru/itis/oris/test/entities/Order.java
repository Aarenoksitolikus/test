package ru.itis.oris.test.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    int id;
    int user_id;
    double total_price;
    boolean status;
}
