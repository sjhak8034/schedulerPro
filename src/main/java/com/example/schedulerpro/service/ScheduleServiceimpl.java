package com.example.schedulerpro.service;

import com.example.schedulerpro.dto.response.schedule.ScheduleDeleteResponseDto;
import com.example.schedulerpro.dto.response.schedule.ScheduleModifyResponseDto;
import com.example.schedulerpro.dto.response.schedule.SchedulePostResponseDto;
import com.example.schedulerpro.dto.response.schedule.ScheduleSearchResponseDto;
import com.example.schedulerpro.dto.response.schedule.ScheduleViewResponseDto;
import com.example.schedulerpro.dto.service.schedule.ScheduleDeleteServiceDto;
import com.example.schedulerpro.dto.service.schedule.ScheduleModifyServiceDto;
import com.example.schedulerpro.dto.service.schedule.SchedulePostServiceDto;
import com.example.schedulerpro.dto.service.schedule.ScheduleSearchServiceDto;
import com.example.schedulerpro.dto.service.schedule.ScheduleViewServiceDto;
import com.example.schedulerpro.entity.Schedule;
import com.example.schedulerpro.entity.User;
import com.example.schedulerpro.repository.schedule.ScheduleRepository;
import com.example.schedulerpro.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ScheduleServiceimpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    @Autowired
    public ScheduleServiceimpl(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }
    @Override
    public SchedulePostResponseDto saveSchedule(SchedulePostServiceDto dto) {
        User user = userRepository.findByUserId(dto.getUserId());
        Schedule schedule = new Schedule(dto.getTitle(), dto.getContent(), user, user.getUser_name());
        try {
            scheduleRepository.save(schedule);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return new SchedulePostResponseDto(
                schedule.getSchedule_id(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser_name(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
    @Override
    public ScheduleViewResponseDto viewSchedule(ScheduleViewServiceDto dto) {
        Schedule schedule = scheduleRepository.findByScheduleId(dto.getScheduleId());
        checkUserId(schedule.getUser().getUser_id(), dto.getUserId());
        return new ScheduleViewResponseDto(
                schedule.getSchedule_id(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser_name(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
    @Override
    public ScheduleDeleteResponseDto deleteSchedule(ScheduleDeleteServiceDto dto) {
        Schedule schedule = scheduleRepository.findByScheduleId(dto.getScheduleId());
        checkUserId(schedule.getUser().getUser_id(), dto.getUserId());
        scheduleRepository.deleteById(dto.getScheduleId());
        return new ScheduleDeleteResponseDto(schedule.getSchedule_id());
    }
    @Override
    public ScheduleModifyResponseDto updateSchedule(ScheduleModifyServiceDto dto) {
        Schedule schedule = scheduleRepository.findByScheduleId(dto.getScheduleId());
        checkUserId(schedule.getUser().getUser_id(), dto.getUserId());
        schedule.update(dto.getTitle(), dto.getContent());
        scheduleRepository.save(schedule);
        return new ScheduleModifyResponseDto(
                schedule.getSchedule_id(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser_name(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
    @Override
    public List<ScheduleSearchResponseDto> getScheduleList(ScheduleSearchServiceDto dto) {
        List<Schedule> scheduleList = scheduleRepository.findByDate(dto.getUserId(),dto.getStartDate(),dto.getEndDate());
        List<ScheduleSearchResponseDto> scheduleSearchResponseDtoList = new ArrayList<>();
        for(Schedule schedule: scheduleList ){
            scheduleSearchResponseDtoList.add(new ScheduleSearchResponseDto(schedule));
        }
        return scheduleSearchResponseDtoList;
    }

    private void checkUserId (Long userId, Long dtoUserId)throws ResponseStatusException {
        if (!Objects.equals(userId, dtoUserId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "유저 아이디가 일치하지 않습니다");
        }
    }
}
