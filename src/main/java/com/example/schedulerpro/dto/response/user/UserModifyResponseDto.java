package com.example.schedulerpro.dto.response.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@Getter

public class UserModifyResponseDto {
    private final String userName;
    private final String email;
    private final LocalDateTime modifiedAt;
    private final LocalDateTime createdAt;
    public UserModifyResponseDto(String userName, String email, LocalDateTime modifiedAt, LocalDateTime createdAt) {
        this.userName = userName;
        this.email = email;
        this.modifiedAt = modifiedAt;
        this.createdAt = createdAt;
    }
}
