package com.company.service;

import com.company.entity.Student;
import com.company.payload.ApiResponse;
import com.company.payload.StudentDto;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudent();

    Student getByStudenId(Integer id);

    ApiResponse addStudent(StudentDto studentDto);

    ApiResponse editStudent(Integer id, StudentDto studentDto);

    ApiResponse deleteStudent(Integer id);
}
