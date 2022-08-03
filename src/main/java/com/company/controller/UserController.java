package com.company.controller;

import com.company.entity.User;
import com.company.payload.ApiResponse;
import com.company.payload.UserDto;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @PreAuthorize(value ="hasAuthority('VIEW_USERS')")
    @GetMapping
    public HttpEntity<?> getAllUser(){
        List<User> users=userService.getAllUser();
        return ResponseEntity.ok(users);
    }
    @PreAuthorize(value ="hasAuthority('VIEW_USERS')")
    @GetMapping("/{id}")
    public HttpEntity<?> getByUserId(@PathVariable Integer id){
        User user=userService.getByUserId(id);
        return ResponseEntity.ok(user);
    }
    @PreAuthorize(value ="hasAuthority('ADD_USER')")
    @PostMapping
    public HttpEntity<?> addUser(@Valid @RequestBody UserDto userDto){
        ApiResponse apiResponse=userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('EDIT_USER')")
    @PutMapping("/{id}")
    public HttpEntity<?> editUser(@Valid @PathVariable Integer id, @RequestBody UserDto userDto){
        ApiResponse apiResponse=userService.editUser(id,userDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('DELETE_USER')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteUser(@PathVariable Integer id){
        ApiResponse apiResponse=userService.deleteUser(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
}
