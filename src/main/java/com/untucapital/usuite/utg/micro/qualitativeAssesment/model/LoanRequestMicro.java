package com.untucapital.usuite.utg.micro.qualitativeAssesment.model;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class LoanRequestMicro extends AbstractEntity {
    @Column(nullable = false)
    private String loanId;
    private String clientMaturity;
    private double clientAmount;
    private double clientInstallments;
    private double clientInterestRate;
    private double proposeAmount;
    private String proposedMaturity;
    private double proposedInstallments;
    private double proposedInterestRate;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getClientMaturity() {
        return clientMaturity;
    }

    public void setClientMaturity(String clientMaturity) {
        this.clientMaturity = clientMaturity;
    }

    public double getClientAmount() {
        return clientAmount;
    }

    public void setClientAmount(double clientAmount) {
        this.clientAmount = clientAmount;
    }

    public double getClientInstallments() {
        return clientInstallments;
    }

    public void setClientInstallments(double clientInstallments) {
        this.clientInstallments = clientInstallments;
    }

    public double getClientInterestRate() {
        return clientInterestRate;
    }

    public void setClientInterestRate(double clientInterestRate) {
        this.clientInterestRate = clientInterestRate;
    }

    public double getProposeAmount() {
        return proposeAmount;
    }

    public void setProposeAmount(double proposeAmount) {
        this.proposeAmount = proposeAmount;
    }

    public String getProposedMaturity() {
        return proposedMaturity;
    }

    public void setProposedMaturity(String proposedMaturity) {
        this.proposedMaturity = proposedMaturity;
    }

    public double getProposedInstallments() {
        return proposedInstallments;
    }

    public void setProposedInstallments(double proposedInstallments) {
        this.proposedInstallments = proposedInstallments;
    }

    public double getProposedInterestRate() {
        return proposedInterestRate;
    }

    public void setProposedInterestRate(double proposedInterestRate) {
        this.proposedInterestRate = proposedInterestRate;
    }
}
