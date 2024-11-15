package com.example.schedulerpro.config.dto.schedule;

import com.example.schedulerpro.dto.request.schedule.ScheduleModifyRequestDto;
import com.example.schedulerpro.dto.request.schedule.SchedulePostRequestDto;
import com.example.schedulerpro.dto.service.schedule.ScheduleDeleteServiceDto;
import com.example.schedulerpro.dto.service.schedule.ScheduleModifyServiceDto;
import com.example.schedulerpro.dto.service.schedule.SchedulePostServiceDto;
import com.example.schedulerpro.dto.service.schedule.ScheduleSearchServiceDto;
import com.example.schedulerpro.dto.service.schedule.ScheduleViewServiceDto;

import java.time.LocalDateTime;


public class ScheduleServiceDtoConfigMapper {

    public static SchedulePostServiceDto toPostServiceDto(SchedulePostRequestDto dto, Long userId) {
        return SchedulePostServiceDto.getInstance(dto,userId);
    }

    public static ScheduleModifyServiceDto toModifyServiceDto(ScheduleModifyRequestDto dto, Long scheduleId, Long userId) {
        return ScheduleModifyServiceDto.getInstance(dto,scheduleId ,userId);
    }

    public static ScheduleDeleteServiceDto toDeleteServiceDto(Long scheduleId, Long userId) {
        return ScheduleDeleteServiceDto.getInstance(scheduleId, userId);
    }

    public static ScheduleViewServiceDto toViewServiceDto(Long scheduleId, Long userId) {
        return ScheduleViewServiceDto.getInstance(scheduleId, userId);
    }

    public static ScheduleSearchServiceDto toSearchServiceDto(LocalDateTime startDate, LocalDateTime endDate, Long userId) {
        return ScheduleSearchServiceDto.getInstance(startDate,endDate,userId);
    }
}
