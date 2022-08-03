package com.company.controller;

import com.company.entity.Teacher;
import com.company.payload.ApiResponse;
import com.company.payload.TeacherDto;
import com.company.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @PreAuthorize(value ="hasAuthority('VIEW_TEACHER')")
    @GetMapping
    public HttpEntity<?> getAllTeacher(){
        List<Teacher> teacherList=teacherService.getAllTeacher();
        return ResponseEntity.ok(teacherList);
    }
    @PreAuthorize(value ="hasAuthority('VIEW_TEACHER')")
    @GetMapping("/{id}")
    public HttpEntity<?> getByTeacherId(@PathVariable Integer id){
        Teacher teacher=teacherService.getByTeacherId(id);
        return ResponseEntity.ok(teacher);
    }
    @PreAuthorize(value ="hasAuthority('ADD_TEACHER')")
    @PostMapping
    public HttpEntity<?> addTeacher(@Valid @RequestBody TeacherDto teacherDto){
        ApiResponse apiResponse=teacherService.addTeacher(teacherDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('EDIT_TEACHER')")
    @PutMapping("/{id}")
    public HttpEntity<?> editTeacher(@Valid @PathVariable Integer id, @RequestBody TeacherDto teacherDto){
        ApiResponse apiResponse=teacherService.editTeacher(id,teacherDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('DELETE_TEACHER')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTeacherId(@PathVariable Integer id){
        ApiResponse apiResponse=teacherService.deleteTeacherId(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
}
