package com.example.schedulerpro.controller.user;

import com.example.schedulerpro.Common.Const;
import com.example.schedulerpro.dto.request.user.UserModifyRequestDto;
import com.example.schedulerpro.dto.request.user.UserSignInRequestDto;
import com.example.schedulerpro.dto.request.user.UserSignUpRequestDto;
import com.example.schedulerpro.dto.response.user.UserModifyResponseDto;
import com.example.schedulerpro.dto.response.user.UserSignInResponseDto;
import com.example.schedulerpro.dto.response.user.UserSignUpResponseDto;
import com.example.schedulerpro.dto.service.user.UserModifyServiceDto;
import com.example.schedulerpro.dto.service.user.UserSignInServiceDto;
import com.example.schedulerpro.dto.service.user.UserSignUpServiceDto;
import com.example.schedulerpro.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Slf4j
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleException(ResponseStatusException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
    }
    @GetMapping("/users/sign-in")
    public UserSignInResponseDto login(@Validated @RequestBody UserSignInRequestDto userSignInRequestDto, BindingResult bindingResult,
                                       HttpServletRequest request, HttpServletResponse response) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if(bindingResult.hasErrors()) {
            log.info("validation errors={}", allErrors);
            // Field, Object Error 모두 JSON으로 반환

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, allErrors.toString());
        }
        HttpSession session = request.getSession();
        if (session != null && request.getSession().getAttribute(Const.LOGIN_USER) != null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이미 로그인이 되어있습니다");
        }
        UserSignInServiceDto serviceDto = UserSignInServiceDto.getInstance(userSignInRequestDto.getEmail(), userSignInRequestDto.getPassword());
        UserSignInResponseDto responseDto = userService.login(serviceDto);


        // Session에 로그인 회원 정보를 저장한다.
        session.setAttribute(Const.LOGIN_USER, responseDto.getUserId());
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        response.addCookie(cookie);
        return responseDto;
    }
    @PostMapping("/users/sign-up")
    public UserSignUpResponseDto register(@Validated @RequestBody UserSignUpRequestDto userSignUpRequestDto, BindingResult bindingResult,
                                          HttpServletRequest request, HttpServletResponse response) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if(bindingResult.hasErrors()) {
            log.info("validation errors={}", allErrors);
            // Field, Object Error 모두 JSON으로 반환

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, allErrors.toString());
        }
        HttpSession session = request.getSession(false);
        if (session != null && request.getSession().getAttribute(Const.LOGIN_USER) != null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이미 로그인이 되어있습니다");
        }
        UserSignUpServiceDto serviceDto = UserSignUpServiceDto.getInstance(userSignUpRequestDto.getUserName(), userSignUpRequestDto.getPassword(),userSignUpRequestDto.getEmail());
        UserSignUpResponseDto responseDto = userService.register(serviceDto);

        return responseDto;
    }
    @PutMapping("/users/profile")
    public UserModifyResponseDto modify(@Validated @RequestBody UserModifyRequestDto userModifyRequestDto, BindingResult bindingResult,
                                        HttpServletRequest request, HttpServletResponse response) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if(bindingResult.hasErrors()) {
            log.info("validation errors={}", allErrors);
            // Field, Object Error 모두 JSON으로 반환

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, allErrors.toString());
        }
        HttpSession session = request.getSession(false);
        // Session에 로그인 회원 정보를 저장한다.


        UserModifyServiceDto serviceDto = UserModifyServiceDto.getInstance((Long)session.getAttribute(Const.LOGIN_USER), userModifyRequestDto.getUserName(),
                userModifyRequestDto.getPassword(), userModifyRequestDto.getEmail());
        UserModifyResponseDto responseDto = userService.updateUser(serviceDto);

        return responseDto;
    }
}
