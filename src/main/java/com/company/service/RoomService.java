package com.company.service;

import com.company.entity.Room;
import com.company.payload.ApiResponse;
import com.company.payload.RoomDto;

import java.util.List;

public interface RoomService {
    List<Room> getAllRoom();

    Room getByRoomId(Integer id);

    ApiResponse addRoom(RoomDto roomDto);

    ApiResponse editRoom(Integer id, RoomDto roomDto);

    ApiResponse deleteRoom(Integer id);
}
