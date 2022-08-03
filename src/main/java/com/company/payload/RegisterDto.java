package com.company.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDto {
    @NotNull(message = "fullName bush bulmasin")
    private String fullName;
    @NotNull(message = "email bush bulmasin")
    private String email;
    @NotNull(message = "email bush bulmasin")
    private String username;
    @NotNull(message = "password bush bulmasin")
    private String password;
    @NotNull(message = "parol takrori bush bulmasin")
    private String prePassword;
}
