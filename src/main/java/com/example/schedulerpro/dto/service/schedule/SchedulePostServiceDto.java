package com.example.schedulerpro.dto.service.schedule;

import com.example.schedulerpro.dto.request.schedule.SchedulePostRequestDto;
import lombok.Getter;

@Getter
public class SchedulePostServiceDto {
    private final String title;
    private final String content;
    private final Long userId;
    private SchedulePostServiceDto(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }
    public static SchedulePostServiceDto getInstance(SchedulePostRequestDto dto, Long userId) {
        return new SchedulePostServiceDto(dto.getTitle(), dto.getContent(), userId);
    }
}
