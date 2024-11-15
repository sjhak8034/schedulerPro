package com.example.schedulerpro.dto.response.schedule;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ScheduleDeleteResponseDto {
    private final Long scheduleId;

    public ScheduleDeleteResponseDto(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
}
