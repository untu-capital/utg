package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class XdsFileUpload extends AbstractEntity{

    @NotNull
    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "file_name")
    private String fileName;

    @NotNull
    @Column(name = "user_id", nullable = false)
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
