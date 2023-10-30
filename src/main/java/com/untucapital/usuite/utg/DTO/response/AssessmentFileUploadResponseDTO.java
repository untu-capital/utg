package com.untucapital.usuite.utg.DTO.response;

import com.sun.istack.NotNull;
import com.untucapital.usuite.utg.DTO.AbstractEntityDTO;
import com.untucapital.usuite.utg.model.AbstractEntity;
import jdk.jfr.Registered;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@RequiredArgsConstructor
public class AssessmentFileUploadResponseDTO extends AbstractEntityDTO {

    private String loanId;
    private String fileName;
    private String userId;
}
