package com.company.payload;

import com.company.entity.Role;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    @NotNull(message = "fullName bush bulmasin")
    private String fullName;
    @NotNull(message = "email bush bulmasin")
    private String email;
    @NotNull(message = "email bush bulmasin")
    private String username;
    @NotNull(message = "password bush bulmasin")
    private String password;
    @NotNull(message = "roleId bush bulmasin")
    private Integer roleId;

}
