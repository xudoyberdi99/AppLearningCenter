package com.company.controller;

import com.company.entity.Group;
import com.company.payload.ApiResponse;
import com.company.payload.GroupDto;
import com.company.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {
    @Autowired
    GroupService groupService;
    @PreAuthorize(value ="hasAuthority('VIEW_GROUPS')")
    @GetMapping
    public HttpEntity<?> getAllGroup(){
        List<Group> groups=groupService.getAllGroup();
        return ResponseEntity.ok(groups);
    }
    @PreAuthorize(value ="hasAuthority('VIEW_GROUPS')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getByGroupId(@PathVariable Integer id){
        return ResponseEntity.ok(groupService.getByGroupId(id));
    }
    @PreAuthorize(value ="hasAuthority('ADD_GROUP')")
    @PostMapping
    public HttpEntity<?> addGroup(@Valid @RequestBody GroupDto groupDto){
        ApiResponse apiResponse=groupService.addGroup(groupDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('EDIT_GROUP')")
    @PutMapping("/{id}")
    public HttpEntity<?> editGroup(@Valid @PathVariable Integer id, @RequestBody GroupDto groupDto){
        ApiResponse apiResponse=groupService.editGroup(id,groupDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('DELETE_GROUP')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteGroup(@PathVariable Integer id){
        ApiResponse apiResponse=groupService.deleteGroup(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
}
