package com.example.schedulerpro.controller.schedule;

import com.example.schedulerpro.Common.Const;
import com.example.schedulerpro.config.dto.schedule.ScheduleServiceDtoConfigMapper;
import com.example.schedulerpro.dto.request.schedule.ScheduleDeleteRequestDto;
import com.example.schedulerpro.dto.request.schedule.ScheduleModifyRequestDto;
import com.example.schedulerpro.dto.request.schedule.SchedulePostRequestDto;
import com.example.schedulerpro.dto.request.schedule.ScheduleSearchRequestDto;
import com.example.schedulerpro.dto.request.schedule.ScheduleViewRequestDto;
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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;
    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleException(ResponseStatusException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
    }

    @PostMapping("schedules")
    public ResponseEntity<SchedulePostResponseDto> postSchedule(@Validated @RequestBody SchedulePostRequestDto dto,
                                                BindingResult bindingResult, HttpServletRequest request) throws IOException {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if(bindingResult.hasErrors()) {
            log.info("validation errors={}", allErrors);
            // Field, Object Error 모두 JSON으로 반환

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, allErrors.toString());
        }
        HttpSession session = request.getSession(false);

        SchedulePostServiceDto schedulePostServiceDto=ScheduleServiceDtoConfigMapper.toPostServiceDto(dto,(Long)session.getAttribute(Const.LOGIN_USER));
        SchedulePostResponseDto schedulePostResponseDto = scheduleService.saveSchedule(schedulePostServiceDto);

        return new ResponseEntity<>(schedulePostResponseDto, HttpStatus.OK);
    }
    @GetMapping("schedules/")
    public ResponseEntity<List<ScheduleSearchResponseDto>> searchSchedule(@RequestParam @NotNull LocalDateTime startDate, @NotNull @RequestParam LocalDateTime endDate,
                                                                     HttpServletRequest request) throws IOException {

        HttpSession session = request.getSession(false);
        ScheduleSearchServiceDto scheduleSearchServiceDto = ScheduleServiceDtoConfigMapper.toSearchServiceDto(startDate,
                endDate,(Long)session.getAttribute(Const.LOGIN_USER));
        List<ScheduleSearchResponseDto> scheduleSearchResponseDto = scheduleService.getScheduleList(scheduleSearchServiceDto);
        return new ResponseEntity<>(scheduleSearchResponseDto,HttpStatus.OK);
    }
    @GetMapping("schedules/{scheduleId}")
    public ResponseEntity<ScheduleViewResponseDto> viewSchedule(@PathVariable(name = "scheduleId") @NotNull @Min(1) Long scheduleId ,
                                                            HttpServletRequest request)throws IOException {

        HttpSession session = request.getSession(false);
        ScheduleViewServiceDto scheduleViewServiceDto = ScheduleServiceDtoConfigMapper.toViewServiceDto(scheduleId,
                (Long)session.getAttribute(Const.LOGIN_USER));
        ScheduleViewResponseDto scheduleViewResponseDto = scheduleService.viewSchedule(scheduleViewServiceDto);
        return new ResponseEntity<>(scheduleViewResponseDto,HttpStatus.OK);
    }

    @PutMapping("schedules/{scheduleId}")
    public ResponseEntity<ScheduleModifyResponseDto> modifySchedule(@Validated @RequestBody ScheduleModifyRequestDto dto, @PathVariable(name = "scheduleId") Long scheduleId,
                                                    BindingResult bindingResult, HttpServletRequest request){
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if(bindingResult.hasErrors()) {
            log.info("validation errors={}", allErrors);
            // Field, Object Error 모두 JSON으로 반환

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, allErrors.toString());

        }
        HttpSession session = request.getSession(false);
        ScheduleModifyServiceDto scheduleModifyServiceDto = ScheduleServiceDtoConfigMapper.toModifyServiceDto(dto,scheduleId
                ,(Long)session.getAttribute(Const.LOGIN_USER));
        ScheduleModifyResponseDto scheduleModifyResponseDto = scheduleService.updateSchedule(scheduleModifyServiceDto);
        return new ResponseEntity<>(scheduleModifyResponseDto,HttpStatus.OK);
    }
    @DeleteMapping("schedules/{scheduleId}")
    public ResponseEntity<ScheduleDeleteResponseDto> deleteSchedule(@PathVariable @NotNull @Min(1) Long scheduleId,
                                                                   HttpServletRequest request){


        HttpSession session = request.getSession(false);
        ScheduleDeleteServiceDto scheduleDeleteServiceDto = ScheduleServiceDtoConfigMapper.toDeleteServiceDto(scheduleId
                ,(Long)session.getAttribute(Const.LOGIN_USER));
        ScheduleDeleteResponseDto scheduleModifyResponseDto = scheduleService.deleteSchedule(scheduleDeleteServiceDto);
        return new ResponseEntity<>(scheduleModifyResponseDto,HttpStatus.OK);
    }

}
