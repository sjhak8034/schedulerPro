package com.example.schedulerpro.dto.request.schedule;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SchedulePostRequestDto {
    @NotNull
    @Size(min = 1, max = 20)
    private final String title;
    @NotNull
    @Size(min = 1, max = 2000)
    private final String content;
    @NotNull
    @Size(min = 1, max = 20)
    private final String userName;
}
