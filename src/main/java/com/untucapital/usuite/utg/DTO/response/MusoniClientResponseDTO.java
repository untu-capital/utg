package com.untucapital.usuite.utg.DTO.response;

import com.untucapital.usuite.utg.DTO.AbstractEntityDTO;
import com.untucapital.usuite.utg.model.AbstractEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class MusoniClientResponseDTO extends AbstractEntityDTO {


    private String clientId;

    private String accountNo;

    private String externalId;

    private String status;

    private String activationDate;

    private String firstname;

    private String lastname;

    private String displayName;

    private String officeName;

    private String submittedByUsername;

    private String savingsAccountId;

}
