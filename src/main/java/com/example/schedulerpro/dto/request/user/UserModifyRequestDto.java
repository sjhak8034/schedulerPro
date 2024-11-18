package com.example.schedulerpro.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter

public class UserModifyRequestDto {
    @NotNull
    @Size(min = 1, max = 20)
    private final String userName;
    @NotNull
    @Size(min = 1, max = 50)
    @Email
    private final String email;
    @NotNull
    @Size(min = 1, max = 20)
    private final String password;

    private UserModifyRequestDto(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
    private UserModifyRequestDto(){
        this.userName = null;
        this.email = null;
        this.password = null;
    }

}
