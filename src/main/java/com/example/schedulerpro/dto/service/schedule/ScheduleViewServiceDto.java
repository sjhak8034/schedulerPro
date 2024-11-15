package com.example.schedulerpro.dto.service.schedule;

import com.example.schedulerpro.dto.request.schedule.ScheduleViewRequestDto;
import lombok.Getter;
import org.springframework.context.annotation.Bean;

@Getter
public class ScheduleViewServiceDto {
    private final Long scheduleId;
    private final Long userId;
    private ScheduleViewServiceDto(Long scheduleId, Long userId) {
        this.scheduleId = scheduleId;
        this.userId = userId;
    }
    public static ScheduleViewServiceDto getInstance(Long scheduleId, Long userId) {
        return new ScheduleViewServiceDto(scheduleId, userId);
    }
}
