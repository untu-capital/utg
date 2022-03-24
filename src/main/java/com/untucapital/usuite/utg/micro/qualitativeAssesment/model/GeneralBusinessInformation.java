package com.untucapital.usuite.utg.micro.qualitativeAssesment.model;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Takunda Chidanika HpProbook - 24/03/2022
 */

@Entity
@Table( name = "GeneralBusinessInformation")
public class GeneralBusinessInformation extends AbstractEntity {

    private String loanId;
    private String client;
    private String name;
    private String activity;
    private String mainLocation;
    private String locationOfOtherPos;
    private Long numberOfWorkers;
    private String clientName;
    private String legalStatus;
    private String activitySince;
    private String businessPremises;
    private String numberOfWorkingDays;
    private String businessHistoryAndOrganisation;

    public GeneralBusinessInformation() {
    }

    public GeneralBusinessInformation(String loanId, String client, String name, String activity, String mainLocation, String locationOfOtherPos, Long numberOfWorkers, String clientName, String legalStatus, String activitySince, String businessPremises, String numberOfWorkingDays, String businessHistoryAndOrganisation) {
        this.loanId = loanId;
        this.client = client;
        this.name = name;
        this.activity = activity;
        this.mainLocation = mainLocation;
        this.locationOfOtherPos = locationOfOtherPos;
        this.numberOfWorkers = numberOfWorkers;
        this.clientName = clientName;
        this.legalStatus = legalStatus;
        this.activitySince = activitySince;
        this.businessPremises = businessPremises;
        this.numberOfWorkingDays = numberOfWorkingDays;
        this.businessHistoryAndOrganisation = businessHistoryAndOrganisation;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getMainLocation() {
        return mainLocation;
    }

    public void setMainLocation(String mainLocation) {
        this.mainLocation = mainLocation;
    }

    public String getLocationOfOtherPos() {
        return locationOfOtherPos;
    }

    public void setLocationOfOtherPos(String locationOfOtherPos) {
        this.locationOfOtherPos = locationOfOtherPos;
    }

    public Long getNumberOfWorkers() {
        return numberOfWorkers;
    }

    public void setNumberOfWorkers(Long numberOfWorkers) {
        this.numberOfWorkers = numberOfWorkers;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getLegalStatus() {
        return legalStatus;
    }

    public void setLegalStatus(String legalStatus) {
        this.legalStatus = legalStatus;
    }

    public String getActivitySince() {
        return activitySince;
    }

    public void setActivitySince(String activitySince) {
        this.activitySince = activitySince;
    }

    public String getBusinessPremises() {
        return businessPremises;
    }

    public void setBusinessPremises(String businessPremises) {
        this.businessPremises = businessPremises;
    }

    public String getNumberOfWorkingDays() {
        return numberOfWorkingDays;
    }

    public void setNumberOfWorkingDays(String numberOfWorkingDays) {
        this.numberOfWorkingDays = numberOfWorkingDays;
    }

    public String getBusinessHistoryAndOrganisation() {
        return businessHistoryAndOrganisation;
    }

    public void setBusinessHistoryAndOrganisation(String businessHistoryAndOrganisation) {
        this.businessHistoryAndOrganisation = businessHistoryAndOrganisation;
    }

    @Override
    public String toString() {
        return "GeneralBusinessInformationService{" +
                "loanId='" + loanId + '\'' +
                ", client='" + client + '\'' +
                ", name='" + name + '\'' +
                ", activity='" + activity + '\'' +
                ", mainLocation='" + mainLocation + '\'' +
                ", locationOfOtherPos='" + locationOfOtherPos + '\'' +
                ", numberOfWorkers=" + numberOfWorkers +
                ", clientName='" + clientName + '\'' +
                ", legalStatus='" + legalStatus + '\'' +
                ", activitySince='" + activitySince + '\'' +
                ", businessPremises='" + businessPremises + '\'' +
                ", numberOfWorkingDays='" + numberOfWorkingDays + '\'' +
                ", businessHistoryAndOrganisation='" + businessHistoryAndOrganisation + '\'' +
                '}';
    }
}
