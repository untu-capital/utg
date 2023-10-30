package com.untucapital.usuite.utg.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class POSSupplierResponseDTO {

    private Integer id;

    private String name;

    private String address;

    private String phone;

    private String contactPerson;

    private String comment;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
