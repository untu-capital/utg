package com.untucapital.usuite.utg.DTO.response;

import com.sun.istack.NotNull;
import com.untucapital.usuite.utg.DTO.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
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
}
