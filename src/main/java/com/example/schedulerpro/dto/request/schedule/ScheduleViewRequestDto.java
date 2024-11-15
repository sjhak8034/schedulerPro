package com.example.schedulerpro.dto.request.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScheduleViewRequestDto {
    @NotNull
    private final Long scheduleId;
}
