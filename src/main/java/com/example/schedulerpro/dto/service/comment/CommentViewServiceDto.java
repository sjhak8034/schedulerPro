package com.example.schedulerpro.dto.service.comment;

import lombok.Getter;

@Getter
public class CommentViewServiceDto {
    private final Long scheduleId;
    public CommentViewServiceDto(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
}
