package com.example.schedulerpro.dto.service.schedule;

import com.example.schedulerpro.dto.request.schedule.SchedulePostRequestDto;
import lombok.Getter;

@Getter
public class SchedulePostServiceDto {
    private final String title;
    private final String content;
    private final Long userId;
    public SchedulePostServiceDto(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

}
