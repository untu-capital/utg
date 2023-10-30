package com.untucapital.usuite.utg.DTO.request;

import com.untucapital.usuite.utg.DTO.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AssessmentFileUploadRequestDTO extends AbstractEntityDTO {

    private String loanId;
    private String fileName;
    private String userId;
}
