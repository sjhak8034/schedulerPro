package com.example.schedulerpro.dto.request.schedule;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter

public class SchedulePostRequestDto {
    @NotNull
    @Size(min = 1, max = 20)
    private final String title;
    @NotNull
    @Size(min = 1, max = 2000)
    private final String content;
    public SchedulePostRequestDto(){
        this.title = "";
        this.content = "";
    }
    public SchedulePostRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
