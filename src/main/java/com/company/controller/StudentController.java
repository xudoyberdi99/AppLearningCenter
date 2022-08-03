package com.company.controller;

import com.company.entity.Student;
import com.company.payload.ApiResponse;
import com.company.payload.StudentDto;
import com.company.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PreAuthorize(value ="hasAuthority('VIEW_STUDENT')")
    @GetMapping
    public HttpEntity<?> getAllStudent(){
        List<Student> studentList=studentService.getAllStudent();
        return ResponseEntity.ok(studentList);
    }
    @PreAuthorize(value ="hasAuthority('VIEW_STUDENT')")
    @GetMapping("/{id}")
    public HttpEntity<?> getByStudenId(@PathVariable Integer id){
        Student student=studentService.getByStudenId(id);
        return ResponseEntity.ok(student);
    }
    @PreAuthorize(value ="hasAuthority('ADD_STUDENT')")
    @PostMapping
    public HttpEntity<?> addStudent(@Valid @RequestBody StudentDto studentDto){
        ApiResponse apiResponse=studentService.addStudent(studentDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('EDIT_STUDENT')")
    @PutMapping("/{id}")
    public HttpEntity<?> editStudent(@Valid @PathVariable Integer id, @RequestBody StudentDto studentDto){
        ApiResponse apiResponse=studentService.editStudent(id,studentDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('DELETE_STUDENT')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteStudent(@PathVariable Integer id){
        ApiResponse apiResponse=studentService.deleteStudent(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }


}
