package com.example.schedulerpro.dto.service.schedule;

import com.example.schedulerpro.dto.request.schedule.ScheduleSearchRequestDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleSearchServiceDto {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final Long userId;
    private ScheduleSearchServiceDto(LocalDateTime startDate, LocalDateTime endDate, Long userId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }
    public static ScheduleSearchServiceDto getInstance(LocalDateTime startDate, LocalDateTime endDate, Long userId) {
        return new ScheduleSearchServiceDto(startDate, endDate, userId);
    }
}
