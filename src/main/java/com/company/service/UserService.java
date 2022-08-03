package com.company.service;

import com.company.entity.User;
import com.company.payload.ApiResponse;
import com.company.payload.UserDto;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User getByUserId(Integer id);

    ApiResponse addUser(UserDto userDto);

    ApiResponse editUser(Integer id, UserDto userDto);

    ApiResponse deleteUser(Integer id);

}
