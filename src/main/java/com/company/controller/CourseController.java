package com.company.controller;

import com.company.entity.Course;
import com.company.payload.ApiResponse;
import com.company.payload.CourseDto;
import com.company.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @PreAuthorize(value ="hasAuthority('VIEW_COURSES')")
    @GetMapping
    public HttpEntity<?> getAllCourse(){
        List<Course> courseList=courseService.getAllCourse();
        return ResponseEntity.ok(courseList);
    }
    @PreAuthorize(value ="hasAuthority('VIEW_COURSES')")
    @GetMapping("/{id}")
    public HttpEntity<?> getByCourseId(@PathVariable Integer id){
        Course course=courseService.getByCourseId(id);
        return ResponseEntity.ok(course);
    }
    @PreAuthorize(value ="hasAuthority('ADD_COURSE')")
    @PostMapping
    public HttpEntity<?> addCourse(@Valid @RequestBody CourseDto courseDto){
        ApiResponse apiResponse=courseService.addCourse(courseDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('EDIT_COURSE')")
    @PutMapping("/{id}")
    public HttpEntity<?> editCourse(@Valid @PathVariable Integer id, @RequestBody CourseDto courseDto){
        ApiResponse apiResponse=courseService.editStudent(id,courseDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('DELETE_COURSE')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCourse(@PathVariable Integer id){
        ApiResponse apiResponse=courseService.deleteCourse(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }

}
