package com.example.schedulerpro.dto.response.comment;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class CommentViewResponseDto {
    private final Long scheduleId;
    private final Long commentId;
    private final String content;
    private final LocalDateTime modifiedAt;
    public CommentViewResponseDto(final long scheduleId, final long commentId, final String content, LocalDateTime modifiedAt) {
        this.scheduleId = scheduleId;
        this.commentId = commentId;
        this.content = content;
        this.modifiedAt = modifiedAt;
    }
}
