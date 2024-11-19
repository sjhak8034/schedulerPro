package com.example.schedulerpro.dto.request.comment;

import lombok.Getter;

@Getter
public class CommentModifyRequestDto {
    private final String content;
    private CommentModifyRequestDto() {
        this.content = "";
    }
    private CommentModifyRequestDto(String content) {
        this.content = content;
    }
}
