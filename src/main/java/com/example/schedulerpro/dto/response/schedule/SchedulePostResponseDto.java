package com.example.schedulerpro.dto.response.schedule;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class SchedulePostResponseDto {
    private final Long scheduleId;
    private final String title;
    private final String content;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
}
