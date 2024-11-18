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

import java.util.List;

public interface ScheduleService {
    SchedulePostResponseDto saveSchedule(SchedulePostServiceDto dto);

    ScheduleViewResponseDto viewSchedule(ScheduleViewServiceDto dto);

    ScheduleDeleteResponseDto deleteSchedule(ScheduleDeleteServiceDto dto);

    ScheduleModifyResponseDto updateSchedule(ScheduleModifyServiceDto dto);

    List<ScheduleSearchResponseDto> getScheduleList(ScheduleSearchServiceDto dto);
}
