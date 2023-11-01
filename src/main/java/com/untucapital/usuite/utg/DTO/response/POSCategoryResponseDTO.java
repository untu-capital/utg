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
 * @created 11/9/2023
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
public class POSCategoryResponseDTO {

    private Integer id;

    private String name;

    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;

}
