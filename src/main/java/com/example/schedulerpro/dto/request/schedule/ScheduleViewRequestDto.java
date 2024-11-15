package com.example.schedulerpro.dto.request.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ScheduleViewRequestDto {
    @NotNull
    private final Long scheduleId;
    public ScheduleViewRequestDto() { this.scheduleId = null; }
    public ScheduleViewRequestDto(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
}
