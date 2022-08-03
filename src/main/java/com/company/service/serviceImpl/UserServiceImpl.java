package com.company.service.serviceImpl;

import com.company.entity.User;
import com.company.exp.ResourseNotFoundException;
import com.company.payload.ApiResponse;
import com.company.payload.UserDto;
import com.company.repository.RoleRepository;
import com.company.repository.UserRepository;
import com.company.service.UserService;
import com.company.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getByUserId(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(new User());
    }

    @Override
    public ApiResponse addUser(UserDto userDto) {
        boolean exists = userRepository.existsByEmail(userDto.getEmail());
        if (exists){
            return new ApiResponse("already exists email User",false);
        }
        User user=new User(
                userDto.getFullName(),
                userDto.getEmail(),
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                roleRepository.findByName(AppConstants.USER).orElseThrow(()->new ResourseNotFoundException("role","name",AppConstants.USER)),
                true
        );
        return new ApiResponse("add succes user",true);
    }

    @Override
    public ApiResponse editUser(Integer id, UserDto userDto) {
        boolean exists = userRepository.existsByEmailAndIdNot(userDto.getEmail(), id);
        if (exists){
            return new ApiResponse("already exists email user",false);
        }
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            return new ApiResponse("not found user",false);
        }

        User user = optionalUser.get();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setEnabled(true);
        roleRepository.findByName(AppConstants.USER).orElseThrow(()->new ResourseNotFoundException("role","name",AppConstants.USER));

        userRepository.save(user);
        return new ApiResponse("edit user succes",true);
    }

    @Override
    public ApiResponse deleteUser(Integer id) {
        try{
            userRepository.deleteById(id);
            return new ApiResponse("delete User",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
