package com.example.schedulerpro.controller.comment;

import com.example.schedulerpro.Common.Const;
import com.example.schedulerpro.dto.request.comment.CommentModifyRequestDto;
import com.example.schedulerpro.dto.request.comment.CommentPostRequestDto;
import com.example.schedulerpro.dto.response.comment.CommentDeleteResponseDto;
import com.example.schedulerpro.dto.response.comment.CommentModifyResponseDto;
import com.example.schedulerpro.dto.response.comment.CommentPostResponseDto;
import com.example.schedulerpro.dto.response.comment.CommentViewResponseDto;
import com.example.schedulerpro.dto.service.comment.CommentDeleteServiceDto;
import com.example.schedulerpro.dto.service.comment.CommentModifyServiceDto;
import com.example.schedulerpro.dto.service.comment.CommentPostServiceDto;
import com.example.schedulerpro.dto.service.comment.CommentViewServiceDto;
import com.example.schedulerpro.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    @Autowired
    public CommentController(CommentService commentServiceimpl) {
        this.commentService = commentServiceimpl;
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleException(ResponseStatusException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "유효성 검사 실패";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentTypeMismatchException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효성 검사 실패");
    }
    @GetMapping("/{scheduleId}")
    public ResponseEntity<List<CommentViewResponseDto>> getComment(@PathVariable("scheduleId") Long scheduleId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        CommentViewServiceDto commentViewServiceDto = new CommentViewServiceDto(scheduleId);
        List<CommentViewResponseDto> list = commentService.getAllComments(commentViewServiceDto);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/{scheduleId}")
    public ResponseEntity<CommentPostResponseDto> postComment(@Validated @RequestBody CommentPostRequestDto dto, BindingResult bindingResult,
                                                              @PathVariable("scheduleId") Long scheduleId ,HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        CommentPostServiceDto commentPostServiceDto = new CommentPostServiceDto(scheduleId,(Long)session.getAttribute(Const.LOGIN_USER), dto.getContent());
        CommentPostResponseDto commentPostResponseDto = commentService.saveComment(commentPostServiceDto);
        return new ResponseEntity<>(commentPostResponseDto, HttpStatus.OK);
    }
    @DeleteMapping("/{scheduleId}/")
    public ResponseEntity<CommentDeleteResponseDto> deleteComment(@PathVariable("scheduleId") Long scheduleId, @RequestParam(name = "commentId") Long commentId , HttpServletRequest request) {
        CommentDeleteServiceDto commentDeleteServiceDto = new CommentDeleteServiceDto(commentId);

        return new ResponseEntity<>(commentService.deleteComment(commentDeleteServiceDto), HttpStatus.OK);
    }
    @PutMapping("/{scheduleId}/")
    public ResponseEntity<CommentModifyResponseDto> modifyComment(@PathVariable("scheduleId") Long scheduleId, @RequestParam(name = "commentId") Long commentId ,
                                                                  @Validated @RequestBody CommentModifyRequestDto dto, HttpServletRequest request) {
        CommentModifyServiceDto commentModifyServiceDto = new CommentModifyServiceDto(scheduleId,commentId,dto.getContent());

        return new ResponseEntity<>(commentService.modifyComment(commentModifyServiceDto), HttpStatus.OK);
    }
}
