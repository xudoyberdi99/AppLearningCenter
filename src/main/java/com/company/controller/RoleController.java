package com.company.controller;

import com.company.entity.Role;
import com.company.payload.ApiResponse;
import com.company.payload.RoleDto;
import com.company.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PreAuthorize(value ="hasAuthority('ADD_ROLE')")
    @PostMapping
    public HttpEntity<?> addRole(@Valid @RequestBody RoleDto roleDto){
        ApiResponse apiResponse=roleService.addRole(roleDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);

    }
    @PreAuthorize(value ="hasAuthority('EDIT_ROLE')")
    @PutMapping
    public HttpEntity<?> editRole(@Valid @PathVariable Integer id, @RequestBody RoleDto roleDto){
        ApiResponse apiResponse=roleService.editRole(id,roleDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
    @PreAuthorize(value ="hasAuthority('VIEW_ROLES')")
    @GetMapping
    public HttpEntity<?> getAllRole(){
        List<Role> roleList=roleService.getAllRole();
        return ResponseEntity.ok(roleList);
    }
    @PreAuthorize(value ="hasAuthority('VIEW_ROLES')")
    @GetMapping("/{id}")
    public HttpEntity<?> getByRoleId(@PathVariable Integer id){
        Role role=roleService.getByRoleId(id);
        return ResponseEntity.ok(role);
    }
    @PreAuthorize(value ="hasAuthority('DELETE_ROLE')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteRole(@PathVariable Integer id){
        ApiResponse apiResponse=roleService.deleteRole(id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
}
