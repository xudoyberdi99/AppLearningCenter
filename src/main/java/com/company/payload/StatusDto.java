package com.company.payload;

import com.company.entity.enums.EnumStatus;
import lombok.Data;

@Data
public class StatusDto {
    private EnumStatus enumStatus;
    private String description;
}
