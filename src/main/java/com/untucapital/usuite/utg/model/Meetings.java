package com.untucapital.usuite.utg.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "meetings")
public class Meetings extends AbstractEntity{

    @NotNull
    @JoinColumn(nullable = false)
    private String userId;

    @NotNull
    @JoinColumn(nullable = false)
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


