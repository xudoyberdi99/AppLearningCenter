package com.company.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
    @NotNull(message = "username bush bulmasin")
    private String username;
    @NotNull(message = "password bush bulmasin")
    private String password;
}
