package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class RequisitionResponseDTO extends AbstractEntityDTO {

    private String poNumber;

    private String poName;

    private String poTotal;

    private String poCount;

    private String poStatus;
    private String notes;
    private String userId;
    private List<String> approvers;
    private List<String> attachments;

    private String poApprover;
    private String cmsApprover;

    private String fromAccount;
    private String toAccount;
}
