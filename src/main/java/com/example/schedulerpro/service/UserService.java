package com.example.schedulerpro.service;

import com.example.schedulerpro.dto.response.user.UserModifyResponseDto;
import com.example.schedulerpro.dto.response.user.UserSignInResponseDto;
import com.example.schedulerpro.dto.response.user.UserSignUpResponseDto;
import com.example.schedulerpro.dto.service.user.UserModifyServiceDto;
import com.example.schedulerpro.dto.service.user.UserSignInServiceDto;
import com.example.schedulerpro.dto.service.user.UserSignUpServiceDto;

public interface UserService {
    UserSignInResponseDto login (UserSignInServiceDto dto);

    UserSignUpResponseDto register (UserSignUpServiceDto dto);

    UserModifyResponseDto updateUser (UserModifyServiceDto dto);
}
