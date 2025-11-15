package ru.itis.oris.test.model;

import lombok.*;

@Getter
@Setter
@Builder
public class UserEntity {
    private Long id;
    private String username;
    private String hashPassword;
    private String role;
}
