package com.company.service.serviceImpl;

import com.company.entity.Teacher;
import com.company.payload.ApiResponse;
import com.company.payload.TeacherDto;
import com.company.repository.TeacherRepository;
import com.company.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;
    @Override
    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getByTeacherId(Integer id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        return optionalTeacher.orElse(new Teacher());
    }

    @Override
    public ApiResponse addTeacher(TeacherDto teacherDto) {
        boolean exists = teacherRepository.existsByPhone(teacherDto.getPhone());
        if (exists){
            return new ApiResponse("already exists phone  teacher",false);
        }
        Teacher teacher =new Teacher();
        teacher.setFullname(teacherDto.getFullname());
        teacher.setPhone(teacherDto.getPhone());
        teacher.setSalary(teacherDto.getSalary());

        teacherRepository.save(teacher);
        return new ApiResponse("add succes teacher",true);
    }

    @Override
    public ApiResponse editTeacher(Integer id, TeacherDto teacherDto) {
        boolean exists = teacherRepository.existsByPhoneAndIdNot(teacherDto.getPhone(), id);
        if (exists){
            return new ApiResponse("already exists phone teacher",false);
        }
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (!optionalTeacher.isPresent()){
            return new ApiResponse("not found teacher",false);
        }

        Teacher teacher = optionalTeacher.get();
        teacher.setFullname(teacherDto.getFullname());
        teacher.setPhone(teacherDto.getPhone());
        teacher.setSalary(teacherDto.getSalary());

        teacherRepository.save(teacher);

        return new ApiResponse("edit teacher succes",true);
    }

    @Override
    public ApiResponse deleteTeacherId(Integer id) {
        try{
            teacherRepository.deleteById(id);
            return new ApiResponse("delete Course",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
