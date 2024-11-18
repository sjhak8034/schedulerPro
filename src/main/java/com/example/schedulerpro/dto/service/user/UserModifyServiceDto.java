package com.example.schedulerpro.dto.service.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter

public class UserModifyServiceDto {
    private final long userId;
    private final String userName;
    private final String password;
    private final String email;
    public UserModifyServiceDto(long userId, String userName, String password, String email) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
}
