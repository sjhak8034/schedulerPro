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
import com.example.schedulerpro.entity.Comment;
import com.example.schedulerpro.entity.Schedule;
import com.example.schedulerpro.entity.User;
import com.example.schedulerpro.repository.schedule.CommentRepository;
import com.example.schedulerpro.repository.schedule.ScheduleRepository;
import com.example.schedulerpro.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    @Autowired
    public CommentServiceImpl(ScheduleRepository scheduleRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }
    @Override
    public CommentPostResponseDto saveComment(CommentPostServiceDto dto)throws ResponseStatusException {
        User user = userRepository.findByUserId(dto.getUserId());
        Schedule schedule = scheduleRepository.findByScheduleId(dto.getScheduleId());
        Comment comment = new Comment(dto.getContent(), user, schedule);
        try {
            commentRepository.save(comment);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "저장 실패");
        }
        return new CommentPostResponseDto(comment.getSchedule().getSchedule_id(),comment.getCommentId(),comment.getContent());
    }
    @Override
    public List<CommentViewResponseDto> getAllComments(CommentViewServiceDto dto)throws ResponseStatusException {
        List<Comment> comments = commentRepository.findByScheduleId(dto.getScheduleId());
        List<CommentViewResponseDto> commentViewResponseDtos = new ArrayList<>();
        if (!comments.isEmpty()){
            for (Comment comment : comments){
                commentViewResponseDtos.add(new CommentViewResponseDto(comment.getSchedule().getSchedule_id(),comment.getCommentId(),comment.getContent(),comment.getModifiedAt()));
            }
        }
        return commentViewResponseDtos;
    }
    @Override
    public CommentDeleteResponseDto deleteComment(CommentDeleteServiceDto dto) throws ResponseStatusException {
        commentRepository.deleteById(dto.getCommentId());
        return new CommentDeleteResponseDto(dto.getCommentId());
    }
    @Override
    public CommentModifyResponseDto modifyComment(CommentModifyServiceDto dto) throws ResponseStatusException {
        Comment comment = commentRepository.findByCommentId(dto.getCommentId());
        comment.update(dto.getContent());
        commentRepository.save(comment);
        if (comment == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다");
        }
        return new CommentModifyResponseDto(comment.getSchedule().getSchedule_id(),comment.getCommentId(),comment.getContent());
    }

}
