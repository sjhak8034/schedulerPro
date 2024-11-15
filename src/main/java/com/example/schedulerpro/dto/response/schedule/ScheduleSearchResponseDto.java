package com.example.schedulerpro.dto.response.schedule;

import com.example.schedulerpro.entity.Schedule;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ScheduleSearchResponseDto {
    private final List<Schedule> schedules;
}
