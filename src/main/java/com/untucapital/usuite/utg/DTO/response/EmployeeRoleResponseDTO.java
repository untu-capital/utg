package com.untucapital.usuite.utg.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class EmployeeRoleResponseDTO {

    private int id;
    private String name;
    private String description;
    private boolean disabled;
    private boolean systemDefined;
}
