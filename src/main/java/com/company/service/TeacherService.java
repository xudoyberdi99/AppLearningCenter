package com.company.service;

import com.company.entity.Teacher;
import com.company.payload.ApiResponse;
import com.company.payload.TeacherDto;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeacher();

    Teacher getByTeacherId(Integer id);

    ApiResponse addTeacher(TeacherDto teacherDto);

    ApiResponse editTeacher(Integer id, TeacherDto teacherDto);

    ApiResponse deleteTeacherId(Integer id);
}
