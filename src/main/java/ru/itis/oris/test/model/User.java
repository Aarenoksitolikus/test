package ru.itis.oris.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class User {
    private Integer id;
    private String username;
    private String password;
    private String role;
}
