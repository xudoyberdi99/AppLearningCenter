package com.company.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DayDto {
    @NotBlank(message = "Please provide a day name")
    private String name;
}
