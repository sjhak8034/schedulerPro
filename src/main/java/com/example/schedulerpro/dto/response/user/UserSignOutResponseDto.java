package com.example.schedulerpro.dto.response.user;

import lombok.Getter;

@Getter
public class UserSignOutResponseDto {
    private final Long userId;
    public UserSignOutResponseDto(Long userId) {
        this.userId = userId;
    }
}
