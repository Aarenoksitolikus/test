package ru.itis.oris.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserEntity {
    private Integer id;
    private String username;
    private String hashPassword;
    private String role;
}
