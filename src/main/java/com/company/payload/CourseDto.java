package com.company.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CourseDto {
    @NotBlank(message = "Please provide a Course name")
    private String name;
    @NotNull(message ="Please provide a Course price" )
    private double price;
    @NotBlank(message = "Please provide a Course price")
    private String duration;
}
