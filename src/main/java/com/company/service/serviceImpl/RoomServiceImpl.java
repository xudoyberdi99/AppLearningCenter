package com.company.service.serviceImpl;

import com.company.entity.Room;
import com.company.payload.ApiResponse;
import com.company.payload.RoomDto;
import com.company.repository.RoomRepository;
import com.company.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    @Override
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public Room getByRoomId(Integer id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        return optionalRoom.orElse(new Room());
    }

    @Override
    public ApiResponse addRoom(RoomDto roomDto) {
        boolean existsByName = roomRepository.existsByName(roomDto.getName());
        if (existsByName){
            return new ApiResponse("already exist name room",false);
        }
        Room room=new Room();
        room.setName(roomDto.getName());
        room.setCapacity(roomDto.getCapacity());

        roomRepository.save(room);
        return new ApiResponse("add room succes",true);
    }

    @Override
    public ApiResponse editRoom(Integer id, RoomDto roomDto) {
        boolean existsByName = roomRepository.existsByName(roomDto.getName());
        if (existsByName){
            return new ApiResponse("already exist name room",false);
        }
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()){
            return new ApiResponse("not found room",false);
        }
        Room room = optionalRoom.get();
        room.setName(roomDto.getName());
        room.setCapacity(roomDto.getCapacity());
        roomRepository.save(room);
        return new ApiResponse("edit room succes",true);
    }

    @Override
    public ApiResponse deleteRoom(Integer id) {
        try{
            roomRepository.deleteById(id);
            return new ApiResponse("delete room",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
