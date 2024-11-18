package com.example.schedulerpro.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class UserSignInRequestDto {
    @NotNull
    @Email
    @Size(min = 1, max = 50)
    private final String email;
    @NotNull
    @Size(min = 1, max = 20)
    private final String password;
    private UserSignInRequestDto() {
        this.email = "";
        this.password = "";
    }
    private UserSignInRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
