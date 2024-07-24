package com.untucapital.usuite.utg.pos.dto.req;


import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@RequiredArgsConstructor
@Data
public class BudgetRequestDTO extends AbstractEntityDTO {

    private String category;
    private int year;
    private int month;
    private Float amount;

    private String january;
    private String february;
    private String march;
    private String april;
    private String may;
    private String june;
    private String july;
    private String august;
    private String september;
    private String october;
    private String november;
    private String december;



}
