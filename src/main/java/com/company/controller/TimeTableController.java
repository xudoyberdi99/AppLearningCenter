package com.company.controller;

import com.company.entity.TimeTable;
import com.company.payload.ApiResponse;
import com.company.payload.TimeTableDto;
import com.company.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/timeTable")
public class TimeTableController {
    @Autowired
    TimeTableService timeTableService;
    @PreAuthorize(value ="hasAuthority('VIEW_TIME_TABLE')")
    @GetMapping
    public HttpEntity<?> getAllTimeTable(){
        List<TimeTable> timeTableList=timeTableService.getAllTimeTable();
        return ResponseEntity.ok(timeTableList);
    }
    @PreAuthorize(value ="hasAuthority('VIEW_TIME_TABLE')")
    @GetMapping("/{id}")
    public HttpEntity<?> getByTimeTableId(@PathVariable Integer id){
        TimeTable timeTable=timeTableService.getByTimeTableId(id);
        return ResponseEntity.ok(timeTable);
    }
    @PreAuthorize(value ="hasAuthority('ADD_TIME_TABLE')")
    @PostMapping
    public HttpEntity<?> addTimeTable(@Valid @RequestBody TimeTableDto timeTableDto){
        ApiResponse apiResponse=timeTableService.addTimeTable(timeTableDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('EDIT_TIME_TABLE')")
    @PutMapping("/{id}")
    public HttpEntity<?> editTimeTable(@Valid @PathVariable Integer id, @RequestBody TimeTableDto timeTableDto){
        ApiResponse apiResponse=timeTableService.editTimeTable(id,timeTableDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('DELETE_TIME_TABLE')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTimeTable(@PathVariable Integer id){
        ApiResponse apiResponse=timeTableService.deleteTimeTable(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }

}
