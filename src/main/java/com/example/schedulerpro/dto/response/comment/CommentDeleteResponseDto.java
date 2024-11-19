package com.example.schedulerpro.dto.response.comment;

import lombok.Getter;

@Getter
public class CommentDeleteResponseDto {
    private final Long commentId;

    public CommentDeleteResponseDto(long commentId) {
        this.commentId = commentId;
    }
}
