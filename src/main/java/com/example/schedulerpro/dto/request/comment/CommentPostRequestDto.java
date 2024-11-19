package com.example.schedulerpro.dto.request.comment;

import lombok.Getter;

@Getter
public class CommentPostRequestDto {
    private final String content;
    private CommentPostRequestDto(){
        this.content = "";
    }
    private CommentPostRequestDto(String content) {
        this.content = content;
    }
}
