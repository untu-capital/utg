package com.untucapital.usuite.utg.DTO.request;

import com.untucapital.usuite.utg.DTO.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class AverageDailySalesMicroRequestDTO extends AbstractEntityDTO {

    private String loanId;
    private double monday;
    private double tuesday;
    private double wednesday;
    private double thursday;
    private double friday;
    private double saturday;
    private double sunday;

}