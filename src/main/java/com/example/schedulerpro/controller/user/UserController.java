package com.example.schedulerpro.controller.user;

import com.example.schedulerpro.Common.Const;
import com.example.schedulerpro.controller.ErrorLogger;
import com.example.schedulerpro.dto.request.user.UserModifyRequestDto;
import com.example.schedulerpro.dto.request.user.UserSignInRequestDto;
import com.example.schedulerpro.dto.request.user.UserSignUpRequestDto;
import com.example.schedulerpro.dto.response.user.UserModifyResponseDto;
import com.example.schedulerpro.dto.response.user.UserSignInResponseDto;
import com.example.schedulerpro.dto.response.user.UserSignOutResponseDto;
import com.example.schedulerpro.dto.response.user.UserSignUpResponseDto;
import com.example.schedulerpro.dto.service.user.UserModifyServiceDto;
import com.example.schedulerpro.dto.service.user.UserSignInServiceDto;
import com.example.schedulerpro.dto.service.user.UserSignUpServiceDto;
import com.example.schedulerpro.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/users")
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "유효성 검사 실패";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/sign-in")
    public ResponseEntity<UserSignInResponseDto> logIn(@Validated @RequestBody UserSignInRequestDto dto, BindingResult bindingResult,
                                                       HttpServletRequest request, HttpServletResponse response) throws IOException, ResponseStatusException {
        // body값이 요구사항과 맞지않는경우 에러 발생
        ErrorLogger.log(bindingResult);
        // session을 생성
        HttpSession session = request.getSession();
        // service 로 보낼 정보
        UserSignInServiceDto serviceDto = new UserSignInServiceDto(dto.getEmail(), dto.getPassword());
        UserSignInResponseDto responseDto = userService.login(serviceDto);

        // Session에 로그인 회원 정보를 저장한다.
        session.setAttribute(Const.LOGIN_USER, responseDto.getUserId());
        // 쿠키에 생성된 session id 저장
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        // response 헤더에 쿠키를 추가
        response.addCookie(cookie);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserSignUpResponseDto> register(@Validated @RequestBody UserSignUpRequestDto dto, BindingResult bindingResult,
                                                          HttpServletRequest request, HttpServletResponse response) throws IOException, ResponseStatusException {
        // body값이 요구사항과 맞지않는경우 에러 발생
        ErrorLogger.log(bindingResult);
        // 이미 로그인이 되었는지  확인
        HttpSession session = request.getSession(false);
        if (session != null && request.getSession().getAttribute(Const.LOGIN_USER) != null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이미 로그인이 되어있습니다");
        }
        // 로그인 시도
        UserSignUpServiceDto serviceDto = new UserSignUpServiceDto(dto.getUserName(), dto.getPassword(), dto.getEmail());
        // 로그인이 되면 결과를 가져옴
        UserSignUpResponseDto responseDto = userService.register(serviceDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserModifyResponseDto> modify(@Validated @RequestBody UserModifyRequestDto dto, BindingResult bindingResult,
                                                        HttpServletRequest request, HttpServletResponse response) throws IOException, ResponseStatusException {
        // body값이 요구사항과 맞지않는경우 에러 발생
        ErrorLogger.log(bindingResult);
        // Session에 로그인dl 되어있는지 확인
        HttpSession session = request.getSession(false);
        // 유저 정보를 변경
        UserModifyServiceDto serviceDto = new UserModifyServiceDto((Long) session.getAttribute(Const.LOGIN_USER), dto.getUserName(),
                dto.getPassword(), dto.getEmail());
        // 완료되면 결과를 가져옴
        UserModifyResponseDto responseDto = userService.updateUser(serviceDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/sign-out")
    public ResponseEntity<UserSignOutResponseDto> logOut(HttpServletRequest request, HttpServletResponse response) throws IOException, ResponseStatusException {

        // session에서 로그인 정보를 가져옴
        HttpSession session = request.getSession(false);
        // 로그인이 되어있지 않다면 에러를 내보냄
        if (session.getAttribute(Const.LOGIN_USER) == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이미 로그아웃 상태입니다");
        }
        // service 로 보낼 정보

        UserSignOutResponseDto responseDto = new UserSignOutResponseDto((long) session.getAttribute(Const.LOGIN_USER));

        // Session에 로그인 회원 정보를 저장한다.
        session.removeAttribute(Const.LOGIN_USER);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
