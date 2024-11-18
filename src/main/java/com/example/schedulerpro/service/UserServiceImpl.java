package com.example.schedulerpro.service;

import com.example.schedulerpro.dto.response.user.UserModifyResponseDto;
import com.example.schedulerpro.dto.response.user.UserSignInResponseDto;
import com.example.schedulerpro.dto.response.user.UserSignUpResponseDto;
import com.example.schedulerpro.dto.service.user.UserModifyServiceDto;
import com.example.schedulerpro.dto.service.user.UserSignInServiceDto;
import com.example.schedulerpro.dto.service.user.UserSignUpServiceDto;
import com.example.schedulerpro.entity.User;
import com.example.schedulerpro.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserSignInResponseDto login(UserSignInServiceDto dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found"));
        if(Objects.equals(dto.getPassword(), user.getPassword() )) {
            return new UserSignInResponseDto(user.getUser_id(),user.getUser_name(),user.getEmail(),
                    user.getCreatedAt(),user.getModifiedAt());
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Wrong Password");
    }
    @Override
    public UserSignUpResponseDto register(UserSignUpServiceDto dto) {
        User user = new User(dto.getUsername(),dto.getPassword(),dto.getEmail());
        try {
            userRepository.save(user);
        }catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"User already exists");
        }
        return new UserSignUpResponseDto(user.getUser_name(),user.getEmail(),user.getCreatedAt(),user.getModifiedAt());
    }
    @Override
    public UserModifyResponseDto updateUser(UserModifyServiceDto dto) {
        User user = userRepository.findByUserId(dto.getUserId());
        user.update(dto.getPassword(), dto.getUserName(), dto.getEmail());
        try {
            userRepository.save(user);
        }catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"email이 중복되었습니다");
        }
        return new UserModifyResponseDto(user.getUser_name(),user.getEmail(),user.getModifiedAt(),user.getCreatedAt());
    }
}
