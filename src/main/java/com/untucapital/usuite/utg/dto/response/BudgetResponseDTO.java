package com.untucapital.usuite.utg.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@RequiredArgsConstructor
@Data
public class BudgetResponseDTO {


    private Integer id;
    private String category;
    private int year;
    private BigDecimal january;
    private BigDecimal february;
    private BigDecimal march;
    private BigDecimal april;
    private BigDecimal may;
    private BigDecimal june;
    private BigDecimal july;
    private BigDecimal august;
    private BigDecimal september;
    private BigDecimal october;
    private BigDecimal november;
    private BigDecimal december;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
