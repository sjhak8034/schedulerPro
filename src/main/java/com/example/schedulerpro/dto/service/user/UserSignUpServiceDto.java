package com.example.schedulerpro.dto.service.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSignUpServiceDto {
    private final String username;
    private final String password;
    private final String email;
    public static UserSignUpServiceDto getInstance(String username, String password, String email) {
        return new UserSignUpServiceDto(username, password, email);
    }
}
