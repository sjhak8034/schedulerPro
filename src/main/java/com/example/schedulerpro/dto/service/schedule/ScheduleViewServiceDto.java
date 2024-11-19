package com.example.schedulerpro.dto.service.schedule;

import lombok.Getter;

@Getter
public class ScheduleViewServiceDto {
    private final Long scheduleId;
    private final Long userId;
    public ScheduleViewServiceDto(Long scheduleId, Long userId) {
        this.scheduleId = scheduleId;
        this.userId = userId;
    }

}
