package com.company.repository;

import com.company.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    boolean existsByPhone(String phone);
    boolean existsByPhoneAndIdNot(String phone, Integer id);

}
