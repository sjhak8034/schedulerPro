package com.example.schedulerpro.dto.service.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter

public class UserSignInServiceDto {
    private final String email;
    private final String password;
    public UserSignInServiceDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
