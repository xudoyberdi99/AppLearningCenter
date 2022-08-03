package com.company.service.serviceImpl;

import com.company.entity.Day;
import com.company.entity.Group;
import com.company.entity.TimeTable;
import com.company.payload.ApiResponse;
import com.company.payload.TimeTableDto;
import com.company.repository.DayRepository;
import com.company.repository.GroupRepository;
import com.company.repository.TimeTableRepository;
import com.company.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TimeTableServiceImpl implements TimeTableService {
    @Autowired
    TimeTableRepository timeTableRepository;

    @Autowired
    DayRepository dayRepository;

    @Autowired
    GroupRepository groupRepository;

    @Override
    public List<TimeTable> getAllTimeTable() {
        return timeTableRepository.findAll();
    }

    @Override
    public TimeTable getByTimeTableId(Integer id) {
        Optional<TimeTable> timeTable = timeTableRepository.findById(id);
        return timeTable.orElse(new TimeTable());
    }

    @Override
    public ApiResponse addTimeTable(TimeTableDto timeTableDto) {

        Optional<Day> dayOptional = dayRepository.findById(timeTableDto.getDayId());
        if (!dayOptional.isPresent()){
            return new ApiResponse("not found day id",false);
        }
        Set<Group> groups=new HashSet<>();
        for (Integer integer : timeTableDto.getGroupList()) {
            Group group = groupRepository.getById(integer);
            groups.add(group);
        }


        TimeTable timeTable=new TimeTable();
        timeTable.setDay(dayOptional.get());
        timeTable.setGroups(groups);
        timeTable.setStartTime(Timestamp.from(Instant.now()));
        timeTable.setEndTime(Timestamp.from(Instant.now().plusSeconds(2*60*60)));

        timeTableRepository.save(timeTable);
        return new ApiResponse("add timeTable succes",true);
    }

    @Override
    public ApiResponse editTimeTable(Integer id, TimeTableDto timeTableDto) {
        Optional<TimeTable> optionalTimeTable = timeTableRepository.findById(id);
        if (!optionalTimeTable.isPresent()){
            return new ApiResponse("not found time Table",false);
        }

        Optional<Day> dayOptional = dayRepository.findById(timeTableDto.getDayId());
        if (!dayOptional.isPresent()){
            return new ApiResponse("not found day id",false);
        }
        Set<Group> groups=new HashSet<>();
        for (Integer integer : timeTableDto.getGroupList()) {
            Group group = groupRepository.getById(integer);
            groups.add(group);
        }

        TimeTable timeTable = optionalTimeTable.get();

        timeTable.setDay(dayOptional.get());
        timeTable.setGroups(groups);
        timeTable.setStartTime(Timestamp.from(Instant.now()));
        timeTable.setEndTime(Timestamp.from(Instant.now().plusSeconds(2*60*60)));
        timeTableRepository.save(timeTable);

        return new ApiResponse("edit TimeTable Succes",true);
    }

    @Override
    public ApiResponse deleteTimeTable(Integer id) {
        try{
            timeTableRepository.deleteById(id);
            return new ApiResponse("delete student",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
