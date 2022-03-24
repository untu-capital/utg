package com.untucapital.usuite.utg.micro.qualitativeAssesment.model;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class FamilyUnitAndPersonalExpenses extends AbstractEntity {
    private String loanId;
    private String maritalStatus;
    private String nameOfSpouse;
    private String guarantor;
    private String numberOfChildren;
    private String otherDependents;

    public FamilyUnitAndPersonalExpenses() {
    }

    public FamilyUnitAndPersonalExpenses(String loanId, String maritalStatus, String nameOfSpouse, String guarantor, String numberOfChildren, String otherDependents) {
        this.loanId = loanId;
        this.maritalStatus = maritalStatus;
        this.nameOfSpouse = nameOfSpouse;
        this.guarantor = guarantor;
        this.numberOfChildren = numberOfChildren;
        this.otherDependents = otherDependents;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getNameOfSpouse() {
        return nameOfSpouse;
    }

    public void setNameOfSpouse(String nameOfSpouse) {
        this.nameOfSpouse = nameOfSpouse;
    }

    public String getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(String guarantor) {
        this.guarantor = guarantor;
    }

    public String getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getOtherDependents() {
        return otherDependents;
    }

    public void setOtherDependents(String otherDependents) {
        this.otherDependents = otherDependents;
    }

    @Override
    public String toString() {
        return "FamilyUnitAndPersonalExpenses{" +
                "loanId='" + loanId + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", nameOfSpouse='" + nameOfSpouse + '\'' +
                ", guarantor='" + guarantor + '\'' +
                ", numberOfChildren='" + numberOfChildren + '\'' +
                ", otherDependents='" + otherDependents + '\'' +
                '}';
    }
}
