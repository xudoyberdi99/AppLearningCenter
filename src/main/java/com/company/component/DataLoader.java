package com.company.component;


import com.company.entity.Role;
import com.company.entity.User;
import com.company.entity.enums.Permissions;
import com.company.repository.RoleRepository;
import com.company.repository.UserRepository;
import com.company.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initialization-mode}")
    private String initialModeType;


    @Override
    public void run(String... args) throws Exception {
        if(initialModeType.equals("always")){
        Permissions[] permissions = Permissions.values();
        Role admin = roleRepository.save(new Role(
                AppConstants.ADMIN,
                Arrays.asList(permissions),
                "sistema egasi"
        ));
        Role user = roleRepository.save(new Role(
                AppConstants.USER,
                Arrays.asList(Permissions.EDIT_USER, Permissions.VIEW_USERS,Permissions.VIEW_COURSES,
                        Permissions.ADD_STATUS,Permissions.EDIT_STATUS,Permissions.DELETE_MY_STATUS,
                        Permissions.VIEW_STATUS),
                "oddiy foydalanivchi"
        ));

        userRepository.save(new User(
                "Admin",
                "xudoyberdi9999@gmail.com",
                "admin",
                passwordEncoder.encode("admin123"),
                         admin,
                true
        ));
        userRepository.save(new User(
                "User",
                "iweguf@gmail.com",
                "user",
                passwordEncoder.encode("user123"),
                         user,
                true
        ));

        }
    }

}
