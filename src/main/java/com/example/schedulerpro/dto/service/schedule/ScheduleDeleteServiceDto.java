package com.example.schedulerpro.dto.service.schedule;

import lombok.Getter;

@Getter
public class ScheduleDeleteServiceDto {
    private final Long scheduleId;
    private final Long userId;
    public ScheduleDeleteServiceDto(Long scheduleId, Long userId) {
        this.scheduleId = scheduleId;
        this.userId = userId;
    }

}
