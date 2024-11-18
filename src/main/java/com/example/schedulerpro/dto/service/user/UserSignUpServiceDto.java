package com.example.schedulerpro.dto.service.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter

public class UserSignUpServiceDto {
    private final String username;
    private final String password;
    private final String email;
    public UserSignUpServiceDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
