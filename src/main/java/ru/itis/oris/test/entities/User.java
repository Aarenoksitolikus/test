package ru.itis.oris.test.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    int id;
    String username;
    String hashPassword;
    String role;

    public User() {}
}
