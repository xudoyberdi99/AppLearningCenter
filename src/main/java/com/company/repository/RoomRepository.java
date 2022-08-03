package com.company.repository;

import com.company.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Integer> {

    boolean existsByName(String name);
}
