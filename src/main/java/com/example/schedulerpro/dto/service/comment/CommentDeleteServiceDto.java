package com.example.schedulerpro.dto.service.comment;

import lombok.Getter;

@Getter
public class CommentDeleteServiceDto {
    private final Long commentId;
    public CommentDeleteServiceDto(long commentId) {
        this.commentId = commentId;
    }
}
