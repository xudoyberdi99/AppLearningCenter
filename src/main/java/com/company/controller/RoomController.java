package com.company.controller;

import com.company.entity.Room;
import com.company.payload.ApiResponse;
import com.company.payload.RoomDto;
import com.company.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    RoomService roomService;
    @PreAuthorize(value ="hasAuthority('VIEW_ROOM')")
    @GetMapping
    public HttpEntity<?> getAllRoom(){
        List<Room> roomList=roomService.getAllRoom();
        return ResponseEntity.ok(roomList);
    }
    @PreAuthorize(value ="hasAuthority('VIEW_ROOM')")
    @GetMapping("/{id}")
    public HttpEntity<?> getByRoomId(@PathVariable Integer id){
        Room room=roomService.getByRoomId(id);
        return ResponseEntity.ok(room);
    }
    @PreAuthorize(value ="hasAuthority('ADD_ROOM')")
    @PostMapping
    public HttpEntity<?> addRoom(@Valid @RequestBody RoomDto roomDto){
        ApiResponse apiResponse=roomService.addRoom(roomDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('EDIT_ROOM')")
    @PutMapping("/{id}")
    public HttpEntity<?> editRoom(@Valid @PathVariable Integer id, @RequestBody RoomDto roomDto){
        ApiResponse apiResponse=roomService.editRoom(id,roomDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('DELETE_ROOM')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteRoom(@PathVariable Integer id){
        ApiResponse apiResponse=roomService.deleteRoom(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
}
