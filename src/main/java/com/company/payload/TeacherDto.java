package com.company.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TeacherDto {
    @NotBlank(message = "Please provide a fullname")
    private String fullname;
    @NotNull(message ="Please provide a salary" )
    private double salary;
    @NotBlank(message = "Please provide a phone")
    private String phone;
    @NotNull(message = "Please provide a roleId")
    private Integer RoleId;
}
