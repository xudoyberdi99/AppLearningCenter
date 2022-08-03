package com.company.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StudentDto {
    @NotBlank(message = "Please provide a student fullName")
    private String fullName;
    @NotBlank(message = "Please provide a phone")
    private String phone;
    @NotNull(message = "Please provide a roleId")
    private Integer roleId;
}
