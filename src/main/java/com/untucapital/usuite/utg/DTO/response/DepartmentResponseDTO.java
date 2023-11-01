package com.untucapital.usuite.utg.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * @author tjchidanika
 * @created 7/9/2023
 */

@RequiredArgsConstructor
@Data
public class DepartmentResponseDTO {

    private Integer id;
    private String name;
}
