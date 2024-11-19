package com.example.schedulerpro.dto.response.comment;

import lombok.Getter;

@Getter
public class CommentPostResponseDto {
    private final Long scheduleId;
    private final Long commentId;
    private final String content;
    public CommentPostResponseDto(long scheduleId, long commentId, String content) {
        this.scheduleId = scheduleId;
        this.commentId = commentId;
        this.content = content;
    }

}
