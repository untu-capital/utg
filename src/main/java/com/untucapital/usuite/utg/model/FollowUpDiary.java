package com.untucapital.usuite.utg.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "FollowUpDiary")
public class FollowUpDiary extends AbstractEntity  {
    private String clientID;
    private String clientName;
    private String clientContactNumber;
    private String clientContactEmail;
    private String clientBusinessAddress;
    private String followUpComments;
    private String contacted;
    private String followUpStatus;

    private String loanOfficerName;



    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientContactNumber() {
        return clientContactNumber;
    }

    public void setClientContactNumber(String clientContactNumber) {
        this.clientContactNumber = clientContactNumber;
    }

    public String getClientContactEmail() {
        return clientContactEmail;
    }

    public void setClientContactEmail(String clientContactEmail) {
        this.clientContactEmail = clientContactEmail;
    }

    public String getClientBusinessAddress() {
        return clientBusinessAddress;
    }

    public void setClientBusinessAddress(String clientBusinessAddress) {
        this.clientBusinessAddress = clientBusinessAddress;
    }

    public String getFollowUpComments() {
        return followUpComments;
    }

    public void setFollowUpComments(String followUpComments) {
        this.followUpComments = followUpComments;
    }

    public String getContacted() {
        return contacted;
    }

    public void setContacted(String contacted) {
        this.contacted = contacted;
    }

    public String getFollowUpStatus() {
        return followUpStatus;
    }

    public void setFollowUpStatus(String followUpStatus) {
        this.followUpStatus = followUpStatus;
    }

    public String getLoanOfficerName() {
        return loanOfficerName;
    }

    public void setLoanOfficerName(String loanOfficerName) {
        this.loanOfficerName = loanOfficerName;
    }
}
