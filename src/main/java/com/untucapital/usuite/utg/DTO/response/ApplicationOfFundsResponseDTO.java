package com.untucapital.usuite.utg.DTO.response;

import com.untucapital.usuite.utg.DTO.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;

@Data
@RequiredArgsConstructor
public class ApplicationOfFundsResponseDTO extends AbstractEntityDTO {

    private String loanId;

    private String details;

    private String totalInvestments;
}
