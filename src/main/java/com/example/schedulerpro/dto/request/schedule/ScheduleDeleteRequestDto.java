package com.example.schedulerpro.dto.request.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public class ScheduleDeleteRequestDto {
    @NotNull
    private final Long scheduleId;
    public ScheduleDeleteRequestDto() {
        this.scheduleId = null;
    }
    public ScheduleDeleteRequestDto(@NotNull Long scheduleId) {
        this.scheduleId = scheduleId;
    }
}
