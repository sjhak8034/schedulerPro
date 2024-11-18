package com.example.schedulerpro.controller.schedule;

import com.example.schedulerpro.Common.Const;
import com.example.schedulerpro.controller.ErrorLogger;

import com.example.schedulerpro.dto.request.schedule.ScheduleModifyRequestDto;
import com.example.schedulerpro.dto.request.schedule.SchedulePostRequestDto;
import com.example.schedulerpro.dto.response.schedule.ScheduleDeleteResponseDto;
import com.example.schedulerpro.dto.response.schedule.ScheduleModifyResponseDto;
import com.example.schedulerpro.dto.response.schedule.SchedulePostResponseDto;
import com.example.schedulerpro.dto.response.schedule.ScheduleSearchResponseDto;
import com.example.schedulerpro.dto.response.schedule.ScheduleViewResponseDto;
import com.example.schedulerpro.dto.service.schedule.ScheduleDeleteServiceDto;
import com.example.schedulerpro.dto.service.schedule.ScheduleModifyServiceDto;
import com.example.schedulerpro.dto.service.schedule.SchedulePostServiceDto;
import com.example.schedulerpro.dto.service.schedule.ScheduleSearchServiceDto;
import com.example.schedulerpro.dto.service.schedule.ScheduleViewServiceDto;
import com.example.schedulerpro.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleException(ResponseStatusException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "유효성 검사 실패";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    //일정을 생성하기 위한 controller
    @PostMapping("/")
    public ResponseEntity<SchedulePostResponseDto> postSchedule(@Validated @RequestBody SchedulePostRequestDto dto,
                                                BindingResult bindingResult, HttpServletRequest request) throws IOException, ResponseStatusException{

        // body값이 요구사항과 맞지않는경우 에러 발생

        ErrorLogger.log(bindingResult);
        // userId를 불러오기 위해 session을 불러옴
        HttpSession session = request.getSession(false);
        // service 로 필요한 정보 전달
        SchedulePostServiceDto schedulePostServiceDto= new SchedulePostServiceDto(dto.getTitle(),dto.getContent(),(Long)session.getAttribute(Const.LOGIN_USER));
        // service 로 부터  response 객체를 받음
        SchedulePostResponseDto schedulePostResponseDto = scheduleService.saveSchedule(schedulePostServiceDto);
        // 성공 출력
        return new ResponseEntity<>(schedulePostResponseDto, HttpStatus.OK);
    }

    //
    @GetMapping("/")
    public ResponseEntity<List<ScheduleSearchResponseDto>> searchSchedule(@RequestParam @NotNull LocalDateTime startDate, @NotNull @RequestParam LocalDateTime endDate,
                                                                     HttpServletRequest request) throws IOException,ResponseStatusException {
        // userId를 불러오기 위해 session을 불러옴
        HttpSession session = request.getSession(false);
        // service 로 필요한 정보 전달
        ScheduleSearchServiceDto scheduleSearchServiceDto = new ScheduleSearchServiceDto(startDate,
                endDate,(Long)session.getAttribute(Const.LOGIN_USER));
        // service 로 부터  response 객체를 받음
        List<ScheduleSearchResponseDto> scheduleSearchResponseDto = scheduleService.getScheduleList(scheduleSearchServiceDto);
        // 성공 출력
        return new ResponseEntity<>(scheduleSearchResponseDto,HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleViewResponseDto> viewSchedule(@PathVariable(name = "scheduleId") @NotNull @Min(1) Long scheduleId ,
                                                            HttpServletRequest request)throws IOException,ResponseStatusException {
        // userId를 불러오기 위해 session을 불러옴
        HttpSession session = request.getSession(false);
        // service 로 필요한 정보 전달
        ScheduleViewServiceDto scheduleViewServiceDto = new ScheduleViewServiceDto(scheduleId,
                (Long)session.getAttribute(Const.LOGIN_USER));
        // service 로 부터  response 객체를 받음;
        ScheduleViewResponseDto scheduleViewResponseDto = scheduleService.viewSchedule(scheduleViewServiceDto);
        // 성공 출력
        return new ResponseEntity<>(scheduleViewResponseDto,HttpStatus.OK);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleModifyResponseDto> modifySchedule(@Validated @RequestBody ScheduleModifyRequestDto dto, @PathVariable(name = "scheduleId") Long scheduleId,
                                                                    BindingResult bindingResult, HttpServletRequest request) throws IOException, ResponseStatusException {
        // body값이 요구사항과 맞지않는경우 에러 발생
        log.info("유효성검사중");
        try {
            ErrorLogger.log(bindingResult);
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        // userId를 불러오기 위해 session을 불러옴
        HttpSession session = request.getSession(false);
        // service 로 필요한 정보 전달
        ScheduleModifyServiceDto scheduleModifyServiceDto = new ScheduleModifyServiceDto(scheduleId,dto.getTitle(), dto.getContent()
                ,(Long)session.getAttribute(Const.LOGIN_USER));
        // service 로 부터  response 객체를 받음;
        ScheduleModifyResponseDto scheduleModifyResponseDto = scheduleService.updateSchedule(scheduleModifyServiceDto);
        // 성공 출력
        return new ResponseEntity<>(scheduleModifyResponseDto,HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDeleteResponseDto> deleteSchedule(@PathVariable @NotNull @Min(1) Long scheduleId,
                                                                   HttpServletRequest request) throws IOException, ResponseStatusException {
        // userId를 불러오기 위해 session을 불러옴
        HttpSession session = request.getSession(false);
        // service 로 필요한 정보 전달
        ScheduleDeleteServiceDto scheduleDeleteServiceDto = new ScheduleDeleteServiceDto(scheduleId
                ,(Long)session.getAttribute(Const.LOGIN_USER));
        // service 로 부터  response 객체를 받음;
        ScheduleDeleteResponseDto scheduleModifyResponseDto = scheduleService.deleteSchedule(scheduleDeleteServiceDto);
        // 성공 출력
        return new ResponseEntity<>(scheduleModifyResponseDto,HttpStatus.OK);
    }

}
