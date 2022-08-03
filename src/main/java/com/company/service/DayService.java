package com.company.service;

import com.company.entity.Day;
import com.company.payload.ApiResponse;
import com.company.payload.DayDto;

import java.util.List;

public interface DayService {
    List<Day> getAllDay();

    Day getByDayId(Integer id);

    ApiResponse addDay(DayDto dayDto);

    ApiResponse editDay(Integer id, DayDto dayDto);

    ApiResponse deleteDay(Integer id);
}
