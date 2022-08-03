package com.company.payload;

import lombok.Data;

import java.util.List;

@Data
public class TimeTableDto {
    private Integer dayId;
    private List<Integer> groupList;
}
