package com.example.schedulerpro.dto.service.comment;

import lombok.Getter;

@Getter
public class CommentPostServiceDto {
    private final Long scheduleId;
    private final Long userId;
    private final String content;
    public CommentPostServiceDto(long scheduleId, long userId,String content) {
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.content = content;
    }
}
