package com.example.schedulerpro.dto.response.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@Getter
@RequiredArgsConstructor
public class UserModifyResponseDto {
    private final String userName;
    private final String email;
    private final LocalDateTime modifiedAt;
    private final LocalDateTime createdAt;
}
