package com.example.schedulerpro.config.dto.user;

import com.example.schedulerpro.dto.request.user.UserModifyRequestDto;
import com.example.schedulerpro.dto.request.user.UserSignInRequestDto;
import com.example.schedulerpro.dto.request.user.UserSignUpRequestDto;
import com.example.schedulerpro.dto.service.user.UserModifyServiceDto;
import com.example.schedulerpro.dto.service.user.UserSignInServiceDto;
import com.example.schedulerpro.dto.service.user.UserSignUpServiceDto;
import com.example.schedulerpro.entity.Schedule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


public class UserServiceDtoConfigMapper {
    public static UserSignInServiceDto toUserSignInServiceDto(UserSignInRequestDto dto) {
        return UserSignInServiceDto.getInstance(dto.getEmail(),dto.getPassword());
    }
    public static UserModifyServiceDto toUserModifyServiceDto(UserModifyRequestDto dto, Long userId) {
        return UserModifyServiceDto.getInstance(userId,dto.getUserName(),dto.getPassword(),dto.getEmail());
    }
    public static UserSignUpServiceDto toUserSignUpServiceDto(UserSignUpRequestDto dto) {
        return UserSignUpServiceDto.getInstance(dto.getUserName(),dto.getPassword(),dto.getEmail());
    }
}
