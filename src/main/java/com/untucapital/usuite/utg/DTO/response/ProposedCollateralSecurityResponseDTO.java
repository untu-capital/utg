package com.untucapital.usuite.utg.DTO.response;

import com.sun.istack.NotNull;
import com.untucapital.usuite.utg.DTO.AbstractEntityDTO;
import com.untucapital.usuite.utg.model.AbstractEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@RequiredArgsConstructor
public class ProposedCollateralSecurityResponseDTO extends AbstractEntityDTO {


    private String loanId;

    private String type;

    private String description;

    private String ownership;

    private String marketValue;

    private String discountValue;


}
