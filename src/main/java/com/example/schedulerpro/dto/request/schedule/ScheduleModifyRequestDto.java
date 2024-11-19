package com.example.schedulerpro.dto.request.schedule;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ScheduleModifyRequestDto {
    @NotNull
    @Size(min = 1, max = 10)
    private final String title;
    @NotNull
    @Size(min = 1, max = 2000)
    private final String content;

    private ScheduleModifyRequestDto() {
        this.title = "";
        this.content = "";

    }
    private ScheduleModifyRequestDto(String title, String content, String userName) {
        this.title = title;
        this.content = content;

    }
}
