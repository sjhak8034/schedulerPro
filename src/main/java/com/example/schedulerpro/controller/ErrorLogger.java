package com.example.schedulerpro.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Slf4j

public class ErrorLogger {
    public static void log(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            log.info("validation errors={}", allErrors);
            // Field, Object Error 모두 JSON으로 반환
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "유효성 검사 실패");
        }
    }
}
