package com.company.repository;

import com.company.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,Integer> {
    boolean existsByNameAndIdNot(String name, Integer id);
    boolean existsByName(String name);
}
