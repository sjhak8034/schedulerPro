package com.example.schedulerpro.dto.service.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSignInServiceDto {
    private final String email;
    private final String password;
    public static UserSignInServiceDto getInstance(String email, String password) {
        return new UserSignInServiceDto(email, password);
    }
}
