package com.company.service;

import com.company.entity.Role;
import com.company.payload.ApiResponse;
import com.company.payload.RoleDto;

import java.util.List;

public interface RoleService {
    ApiResponse addRole(RoleDto roleDto);

    ApiResponse editRole(Integer id,RoleDto roleDto);

    List<Role> getAllRole();

    Role getByRoleId(Integer id);

    ApiResponse deleteRole(Integer id);
}
