package com.untucapital.usuite.utg.DTO.response;

import com.untucapital.usuite.utg.DTO.AbstractEntityDTO;
import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;


public class XdsFileUploadResponseDTO extends AbstractEntityDTO {

    private String loanId;

    private String fileName;

    private String userId;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}