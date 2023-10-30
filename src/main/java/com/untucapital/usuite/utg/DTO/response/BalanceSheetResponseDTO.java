package com.untucapital.usuite.utg.DTO.response;

import com.untucapital.usuite.utg.DTO.AbstractEntityDTO;
import com.untucapital.usuite.utg.model.AbstractEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@RequiredArgsConstructor
@Data
public class BalanceSheetResponseDTO extends AbstractEntityDTO {

    private String id;
    private String loanId;
    private String name;
    private String comment;
    private double amount;

}
