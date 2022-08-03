package com.company.controller;

import com.company.entity.Status;
import com.company.payload.ApiResponse;
import com.company.payload.StatusDto;
import com.company.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    StatusService statusService;

    @PreAuthorize(value ="hasAuthority('VIEW_STATUS')")
    @GetMapping
    public HttpEntity<?> getAllStatus(){
        List<Status> statusList=statusService.getAllStatus();
        return ResponseEntity.ok(statusList);
    }
    @PreAuthorize(value ="hasAuthority('VIEW_STATUS')")
    @GetMapping("/{id}")
    public HttpEntity<?> getByStatusId(@PathVariable Integer id){
        Status status=statusService.getByStatusId(id);
        return ResponseEntity.ok(status);
    }
    @PreAuthorize(value ="hasAuthority('ADD_STATUS')")
    @PostMapping
    public HttpEntity<?> addStatus(@Valid @RequestBody StatusDto statusDto){
        ApiResponse apiResponse=statusService.addStatus(statusDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('EDIT_STATUS')")
    @PutMapping("/{id}")
    public HttpEntity<?> editStatus(@Valid @PathVariable Integer id, @RequestBody StatusDto statusDto){
        ApiResponse apiResponse=statusService.editStatus(id,statusDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('DELETE_MY_STATUS')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteStatus(@PathVariable Integer id){
        ApiResponse apiResponse=statusService.deleteStatus(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
}
