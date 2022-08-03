package com.company.controller;

import com.company.entity.Day;
import com.company.payload.ApiResponse;
import com.company.payload.DayDto;
import com.company.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/day")
public class DayController {

    @Autowired
    DayService dayService;
    @PreAuthorize(value ="hasAuthority('VIEW_DAYS')")
    @GetMapping
    public HttpEntity<?> getAllDay(){
        List<Day> days=dayService.getAllDay();
        return ResponseEntity.ok(days);
    }
    @PreAuthorize(value ="hasAuthority('VIEW_DAYS')")
    @GetMapping("/{id}")
    public HttpEntity<?> getByDayId(@PathVariable Integer id){
        Day day=dayService.getByDayId(id);
        return ResponseEntity.ok(day);
    }
    @PreAuthorize(value ="hasAuthority('ADD_DAY')")
    @PostMapping
    public HttpEntity<?> addDay(@Valid @RequestBody DayDto dayDto){
        ApiResponse apiResponse=dayService.addDay(dayDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('EDIT_DAY')")
    @PutMapping("/{id}")
    public HttpEntity<?> editDay(@Valid @PathVariable Integer id, @RequestBody DayDto dayDto){
        ApiResponse apiResponse=dayService.editDay(id,dayDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('DELETE_DAY')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteDay(@PathVariable Integer id){
        ApiResponse apiResponse=dayService.deleteDay(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
}
