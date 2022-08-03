package com.company.service.serviceImpl;

import com.company.entity.Role;
import com.company.payload.ApiResponse;
import com.company.payload.RoleDto;
import com.company.repository.RoleRepository;
import com.company.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    public ApiResponse addRole(RoleDto roleDto) {
        if (roleRepository.existsByName(roleDto.getName()))
            return new ApiResponse("already exist role ", false);
        Role role=new Role(
                roleDto.getName(),
                roleDto.getPermissionsList(),
                roleDto.getDescription()
        );
        roleRepository.save(role);
        return new ApiResponse("add role succes",true);
    }

    @Override
    public ApiResponse editRole(Integer id,RoleDto roleDto) {
        if (roleRepository.existsByNameAndIdNot(roleDto.getName(),id)){
            return new ApiResponse("already exist role name",false);
        }
        Optional<Role> role = roleRepository.findById(id);
        if (!role.isPresent()){
            return new ApiResponse("not found role",false);
        }
        Role role1 = role.get();
        role1.setName(roleDto.getName());
        role1.setDescription(roleDto.getDescription());
        role1.setPermissionsList(roleDto.getPermissionsList());
        roleRepository.save(role1);
        return new ApiResponse("edit role succes ",true);
    }

    @Override
    public List<Role> getAllRole() {
        List<Role> list = roleRepository.findAll();
        return list;
    }

    @Override
    public Role getByRoleId(Integer id) {
        Optional<Role> role = roleRepository.findById(id);
        return  role.orElse(new Role());
    }

    @Override
    public ApiResponse deleteRole(Integer id) {
        try{
            roleRepository.deleteById(id);
            return new ApiResponse("delete room",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
