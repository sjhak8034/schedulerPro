package com.example.schedulerpro.dto.service.schedule;

import com.example.schedulerpro.dto.request.schedule.ScheduleSearchRequestDto;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ScheduleSearchServiceDto {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final Long userId;
    public ScheduleSearchServiceDto(LocalDate startDate, LocalDate endDate, Long userId) {
        this.startDate = startDate.atTime(0, 0, 0);
        this.endDate = endDate.atTime(0, 0, 0).plusDays(1);
        this.userId = userId;
    }

}
