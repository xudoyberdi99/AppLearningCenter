package com.company.service;

import com.company.entity.Group;
import com.company.payload.ApiResponse;
import com.company.payload.GroupDto;

import java.util.List;

public interface GroupService {
    List<Group> getAllGroup();

    Group getByGroupId(Integer id);

    ApiResponse addGroup(GroupDto groupDto);

    ApiResponse editGroup(Integer id, GroupDto groupDto);

    ApiResponse deleteGroup(Integer id);
}
