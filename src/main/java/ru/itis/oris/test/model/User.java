package ru.itis.oris.test.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String hashPassword;
    private String role;
}