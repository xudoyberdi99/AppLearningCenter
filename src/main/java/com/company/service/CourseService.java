package com.company.service;

import com.company.entity.Course;
import com.company.payload.ApiResponse;
import com.company.payload.CourseDto;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourse();

    Course getByCourseId(Integer id);

    ApiResponse addCourse(CourseDto courseDto);

    ApiResponse editStudent(Integer id, CourseDto courseDto);

    ApiResponse deleteCourse(Integer id);

}
