package com.example.schedulerpro.dto.request.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter

public class ScheduleSearchRequestDto {
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private final LocalDateTime startDate;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private final LocalDateTime endDate;
    @NotNull
    private final Long userId;
    private ScheduleSearchRequestDto(){
        this.startDate = LocalDateTime.now();
        this.endDate = LocalDateTime.now().plusDays(1);
        this.userId = 1L;
    }
    private ScheduleSearchRequestDto(LocalDateTime startDate, LocalDateTime endDate, Long userId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }
}
