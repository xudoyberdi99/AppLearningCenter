package com.company.service.serviceImpl;

import com.company.entity.Student;
import com.company.payload.ApiResponse;
import com.company.payload.StudentDto;
import com.company.repository.StudentRepository;
import com.company.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student getByStudenId(Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.orElse(new Student());
    }

    @Override
    public ApiResponse addStudent(StudentDto studentDto) {
        boolean exists = studentRepository.existsByPhone(studentDto.getPhone());
        if (exists){
            return new ApiResponse("already exists phone  Student",false);
        }
        Student student=new Student();
        student.setFullName(studentDto.getFullName());
        student.setPhone(studentDto.getPhone());

        studentRepository.save(student);
        return new ApiResponse("add student succes",true);
    }

    @Override
    public ApiResponse editStudent(Integer id, StudentDto studentDto) {
        boolean exists = studentRepository.existsByPhoneAndIdNot(studentDto.getPhone(), id);
        if (exists){
            return new ApiResponse("already exists phone Student",false);
        }
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent()){
            return new ApiResponse("not found Student",false);
        }
        Student student = optionalStudent.get();
        student.setPhone(studentDto.getPhone());
        student.setFullName(studentDto.getFullName());
        return new ApiResponse("edit student succes",true);
    }

    @Override
    public ApiResponse deleteStudent(Integer id) {
        try{
            studentRepository.deleteById(id);
            return new ApiResponse("delete student",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
