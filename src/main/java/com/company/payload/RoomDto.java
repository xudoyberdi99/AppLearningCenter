package com.company.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RoomDto {
    @NotBlank(message = "Please provide a Room Name")
    private String name;
    @NotNull(message = "Please provide a capacity")
    private Integer capacity;

}
