package com.company.service;

import com.company.entity.Status;
import com.company.payload.ApiResponse;
import com.company.payload.StatusDto;

import java.util.List;

public interface StatusService {
    List<Status> getAllStatus();

    Status getByStatusId(Integer id);

    ApiResponse addStatus( StatusDto statusDto);

    ApiResponse editStatus(Integer id, StatusDto statusDto);

    ApiResponse deleteStatus(Integer id);
}
