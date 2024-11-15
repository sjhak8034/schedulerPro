package com.example.schedulerpro.dto.service.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserModifyServiceDto {
    private final long userId;
    private final String userName;
    private final String password;
    private final String email;
    public static UserModifyServiceDto getInstance(long userId, String userName, String password, String email) {
        return new UserModifyServiceDto(userId, userName, password, email);
    }
}
