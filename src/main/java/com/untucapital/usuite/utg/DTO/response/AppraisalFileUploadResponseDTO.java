package com.untucapital.usuite.utg.DTO.response;

import com.untucapital.usuite.utg.DTO.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class AppraisalFileUploadResponseDTO extends AbstractEntityDTO {


    private String fileName;

    private String fileType;

    private String fileDescription;

    private String userId;

    private String loanId;

}
