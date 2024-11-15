package com.example.schedulerpro.dto.service.schedule;

import com.example.schedulerpro.dto.request.schedule.ScheduleModifyRequestDto;
import com.example.schedulerpro.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleModifyServiceDto {
    private final Long scheduleId;
    private final String title;
    private final String content;
    private final Long userId;
    private ScheduleModifyServiceDto(Long scheduleId, String title, String content, Long userId) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }
    public static ScheduleModifyServiceDto getInstance(ScheduleModifyRequestDto dto, Long scheduleId, Long userId) {
        return new ScheduleModifyServiceDto(scheduleId, dto.getTitle(), dto.getContent(),userId);
    }
}
