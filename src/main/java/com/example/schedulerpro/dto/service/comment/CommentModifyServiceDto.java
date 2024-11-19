package com.example.schedulerpro.dto.service.comment;

import lombok.Getter;

import java.util.List;

@Getter
public class CommentModifyServiceDto {
    private final Long scheduleId;
    private final Long commentId;
    private final String content;
    public CommentModifyServiceDto(long scheduleId, long commentId, String content) {
        this.scheduleId = scheduleId;
        this.commentId = commentId;
        this.content = content;
    }
}
