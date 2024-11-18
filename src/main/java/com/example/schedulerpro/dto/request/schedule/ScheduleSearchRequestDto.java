package com.example.schedulerpro.dto.request.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter

public class ScheduleSearchRequestDto {
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private final LocalDate startDate;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private final LocalDate endDate;
    @NotNull
    private final Long userId;
    private ScheduleSearchRequestDto(){
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now().plusDays(1);
        this.userId = 1L;
    }
    private ScheduleSearchRequestDto(LocalDate startDate, LocalDate endDate, Long userId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }
}
