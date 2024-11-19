package com.example.schedulerpro.service;

import com.example.schedulerpro.dto.request.comment.CommentModifyRequestDto;
import com.example.schedulerpro.dto.response.comment.CommentDeleteResponseDto;
import com.example.schedulerpro.dto.response.comment.CommentModifyResponseDto;
import com.example.schedulerpro.dto.response.comment.CommentPostResponseDto;
import com.example.schedulerpro.dto.response.comment.CommentViewResponseDto;
import com.example.schedulerpro.dto.service.comment.CommentDeleteServiceDto;
import com.example.schedulerpro.dto.service.comment.CommentModifyServiceDto;
import com.example.schedulerpro.dto.service.comment.CommentPostServiceDto;
import com.example.schedulerpro.dto.service.comment.CommentViewServiceDto;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface CommentService {

    CommentPostResponseDto saveComment(CommentPostServiceDto dto)throws ResponseStatusException;

    List<CommentViewResponseDto> getAllComments(CommentViewServiceDto dto)throws ResponseStatusException;

    CommentDeleteResponseDto deleteComment(CommentDeleteServiceDto dto) throws ResponseStatusException;

    CommentModifyResponseDto modifyComment(CommentModifyServiceDto dto) throws ResponseStatusException;
}
