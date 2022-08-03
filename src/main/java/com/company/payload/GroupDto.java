package com.company.payload;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class GroupDto {

    @NotBlank(message = "Please provide a Group name")
    private String name;

    private Integer courseId;

    private Integer roomId;

    private Integer statusId;

    private Integer teacherId;

    private List<Integer> studentsList;
}
