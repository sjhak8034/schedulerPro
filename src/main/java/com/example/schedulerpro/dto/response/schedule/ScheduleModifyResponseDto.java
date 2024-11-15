package com.example.schedulerpro.dto.response.schedule;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleModifyResponseDto {
    private final Long scheduleId;
    private final String title;
    private final String content;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    public ScheduleModifyResponseDto(Long scheduleId, String title, String content, String userName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
