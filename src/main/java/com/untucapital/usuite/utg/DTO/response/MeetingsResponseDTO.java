package com.untucapital.usuite.utg.DTO.response;

import com.untucapital.usuite.utg.DTO.AbstractEntityDTO;
import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


public class MeetingsResponseDTO extends AbstractEntityDTO {


    private String userId;

    private String loanId;

    private String collateral;

    private String meetingFinalizedBy;

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

    public String getCollateral() {
        return collateral;
    }

    public void setCollateral(String collateral) {
        this.collateral = collateral;
    }

    public String getMeetingFinalizedBy() {
        return meetingFinalizedBy;
    }

    public void setMeetingFinalizedBy(String meetingFinalizedBy) {
        this.meetingFinalizedBy = meetingFinalizedBy;
    }
}

