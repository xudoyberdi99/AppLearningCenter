package com.company.service;

import com.company.entity.TimeTable;
import com.company.payload.ApiResponse;
import com.company.payload.TimeTableDto;

import java.util.List;

public interface TimeTableService {
    List<TimeTable> getAllTimeTable();

    TimeTable getByTimeTableId(Integer id);

    ApiResponse addTimeTable(TimeTableDto timeTableDto);

    ApiResponse editTimeTable(Integer id, TimeTableDto timeTableDto);

    ApiResponse deleteTimeTable(Integer id);
}
