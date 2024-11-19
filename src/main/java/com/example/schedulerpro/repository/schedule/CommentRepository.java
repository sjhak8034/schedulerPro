package com.example.schedulerpro.repository.schedule;

import com.example.schedulerpro.entity.Comment;
import com.example.schedulerpro.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select t from Comment t where t.schedule.schedule_id = ?1 order by t.modifiedAt desc ")
    List<Comment> findByScheduleId(Long scheduleId);

    default void deleteByCommentId(Long commentId) {
        deleteById(commentId);
    }

    default void saveComment(Comment comment) {
        save(comment);
    }
    default Comment findByCommentId(Long commentId) {
        return findById(commentId).orElse(null);
    }
}
