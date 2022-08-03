package com.company.payload;

import com.company.entity.enums.Permissions;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class RoleDto {
    @NotBlank
    private String name;

    private String description;
    @NotEmpty
    private List<Permissions> permissionsList;
}
