package com.example.schedulerpro.repository.schedule;

import com.example.schedulerpro.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    default Schedule findByScheduleId(Long scheduleId) {
        return findById(scheduleId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Does not exist id = " + scheduleId
                        )
                );
    }
    @Query("select t from Schedule t where t.user.user_id = ?1 and t.modifiedAt between ?2 and ?3 order by t.modifiedAt desc ")
    List<Schedule> findByDate(Long userId, LocalDateTime startDate, LocalDateTime endDate);

}
