package com.company.service.serviceImpl;

import com.company.entity.Course;
import com.company.payload.ApiResponse;
import com.company.payload.CourseDto;
import com.company.repository.CourseRepository;
import com.company.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;
    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course getByCourseId(Integer id) {
        Optional<Course> optionalCourse= courseRepository.findById(id);
        return optionalCourse.orElse(new Course());
    }

    @Override
    public ApiResponse addCourse(CourseDto courseDto) {
        boolean existsByName = courseRepository.existsByName(courseDto.getName());
        if (existsByName){
            return new ApiResponse("already exists name course",false);
        }
        Course course=new Course();
        course.setName(courseDto.getName());
        course.setPrice(BigDecimal.valueOf(courseDto.getPrice()));
        course.setDuration(courseDto.getDuration());

        courseRepository.save(course);
        return new ApiResponse("add course succes",true);
    }

    @Override
    public ApiResponse editStudent(Integer id, CourseDto courseDto) {
        boolean exists = courseRepository.existsByNameAndIdNot(courseDto.getName(), id);
        if (exists){
            return new ApiResponse("already exist course name",false);
        }
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (!courseOptional.isPresent()){
            return new ApiResponse("not found Course",false);
        }
        Course course = courseOptional.get();
        course.setName(courseDto.getName());
        course.setPrice(BigDecimal.valueOf(courseDto.getPrice()));
        course.setDuration(courseDto.getDuration());

        courseRepository.save(course);
        return new ApiResponse("edit Course succes",true);
    }

    @Override
    public ApiResponse deleteCourse(Integer id) {
        try{
            courseRepository.deleteById(id);
            return new ApiResponse("delete Course",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
