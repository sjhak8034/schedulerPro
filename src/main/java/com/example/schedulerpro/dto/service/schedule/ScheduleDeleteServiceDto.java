package com.example.schedulerpro.dto.service.schedule;

import com.example.schedulerpro.dto.request.schedule.ScheduleDeleteRequestDto;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public class ScheduleDeleteServiceDto {
    private final Long scheduleId;
    private final Long userId;
    private ScheduleDeleteServiceDto(Long scheduleId, Long userId) {
        this.scheduleId = scheduleId;
        this.userId = userId;
    }
    public static ScheduleDeleteServiceDto getInstance(Long scheduleId, Long userId) {
        return new ScheduleDeleteServiceDto(scheduleId, userId);
    }
}
