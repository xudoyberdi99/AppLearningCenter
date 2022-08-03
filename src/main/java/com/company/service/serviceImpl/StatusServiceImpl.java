package com.company.service.serviceImpl;

import com.company.entity.Status;
import com.company.payload.ApiResponse;
import com.company.payload.StatusDto;
import com.company.repository.StatusRepository;
import com.company.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    StatusRepository statusRepository;
    @Override
    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    @Override
    public Status getByStatusId(Integer id) {
        Optional<Status> status = statusRepository.findById(id);
        return status.orElse(new Status());
    }

    @Override
    public ApiResponse addStatus(StatusDto statusDto) {
        Status status=new Status();
        status.setEnumStatus(statusDto.getEnumStatus());
        status.setDescription(statusDto.getDescription());
        statusRepository.save(status);
        return new ApiResponse("add Status succes",true);
    }

    @Override
    public ApiResponse editStatus(Integer id, StatusDto statusDto) {
        Optional<Status> optionalStatus = statusRepository.findById(id);
        if (!optionalStatus.isPresent()){
            return new ApiResponse("not found status",false);
        }
        Status status = optionalStatus.get();
        status.setEnumStatus(statusDto.getEnumStatus());
        status.setDescription(statusDto.getDescription());

        statusRepository.save(status);
        return new ApiResponse("edit status succes ",true);
    }

    @Override
    public ApiResponse deleteStatus(Integer id) {
        try{
            statusRepository.deleteById(id);
            return new ApiResponse("delete status",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
