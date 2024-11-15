package com.example.schedulerpro.dto.response.schedule;

import com.example.schedulerpro.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleSearchResponseDto {
    private final String title;
    private final String userName;
    private final LocalDateTime modifiedAt;
    public ScheduleSearchResponseDto(Schedule schedule) {
        this.title = schedule.getTitle();
        this.userName = schedule.getUser_name();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
