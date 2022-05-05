package com.untucapital.usuite.utg.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

@Entity
public class AppraisalFileUpload extends  AbstractEntity{
    private String fileName;

    private String fileType;

    private String fileDescription;

    @NotNull
    @JoinColumn(nullable = false)
    private String userId;

    @JoinColumn
    private String loanId;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }
}
