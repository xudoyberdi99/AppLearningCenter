package com.company.service.serviceImpl;

import com.company.entity.*;
import com.company.payload.ApiResponse;
import com.company.payload.GroupDto;
import com.company.repository.*;
import com.company.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StatusRepository statusRepository;

    @Override
    public List<Group> getAllGroup() {
        return groupRepository.findAll();
    }

    @Override
    public Group getByGroupId(Integer id) {
        Optional<Group> group = groupRepository.findById(id);
        return group.orElse(new Group());
    }

    @Override
    public ApiResponse addGroup(GroupDto groupDto) {
        boolean existsByName = groupRepository.existsByName(groupDto.getName());
        if (existsByName){
            return new ApiResponse("alread exist name gruop",false);
        }

        Optional<Course> course = courseRepository.findById(groupDto.getCourseId());
        if (!course.isPresent()){
            return new ApiResponse("not found course",false);
        }

        Optional<Teacher> teacher = teacherRepository.findById(groupDto.getTeacherId());
        if (!teacher.isPresent()){
            return new ApiResponse("not found teacher",false);
        }

        Optional<Status> status = statusRepository.findById(groupDto.getStatusId());
        if (!status.isPresent()){
            return new ApiResponse("not found status",false);
        }
        Set<Student> students=new HashSet<>();
        for (Integer integer : groupDto.getStudentsList()) {
            Student student = studentRepository.getById(integer);
            students.add(student);
        }

        Optional<Room> optionalRoom = roomRepository.findById(groupDto.getRoomId());
        if (!optionalRoom.isPresent()){
            return new ApiResponse("not found room",false);
        }

        Group group=new Group();
        group.setName(groupDto.getName());
        group.setRoom(optionalRoom.get());
        group.setCourse(course.get());
        group.setStudents(students);
        group.setTeacher(teacher.get());
        group.setStatus(status.get());
        group.setStarDate(new Date(System.currentTimeMillis()));
        group.setEndDate(null);

        groupRepository.save(group);
        return new ApiResponse("add Group succes",true);
    }

    @Override
    public ApiResponse editGroup(Integer id, GroupDto groupDto) {
        boolean exists = groupRepository.existsByNameAndIdNot(groupDto.getName(), id);
        if (exists){
            return new ApiResponse("already exist group name",false);
        }
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (!optionalGroup.isPresent()){
            return new ApiResponse("not found group",false);
        }
        Optional<Course> course = courseRepository.findById(groupDto.getCourseId());
        if (!course.isPresent()){
            return new ApiResponse("not found course",false);
        }
        Optional<Teacher> teacher = teacherRepository.findById(groupDto.getTeacherId());
        if (!teacher.isPresent()){
            return new ApiResponse("not found teacher",false);
        }
        Optional<Status> status = statusRepository.findById(groupDto.getStatusId());
        if (!status.isPresent()){
            return new ApiResponse("not found status",false);
        }


        Set<Student> students=new HashSet<>();
        for (Integer integer : groupDto.getStudentsList()) {
            Student student = studentRepository.getById(integer);
            students.add(student);
        }
        Optional<Room> optionalRoom = roomRepository.findById(groupDto.getRoomId());
        if (!optionalRoom.isPresent()){
            return new ApiResponse("not found room",false);
        }

        Group group = optionalGroup.get();
        group.setName(groupDto.getName());
        group.setRoom(optionalRoom.get());
        group.setCourse(course.get());
        group.setTeacher(teacher.get());
        group.setStudents(students);
        group.setStatus(status.get());
        group.setStarDate(new Date(System.currentTimeMillis()));
        group.setEndDate(null);

        groupRepository.save(group);
        return new ApiResponse("edit succes Group",true);
    }

    @Override
    public ApiResponse deleteGroup(Integer id) {
        try{
            groupRepository.deleteById(id);
            return new ApiResponse("delete Group",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
