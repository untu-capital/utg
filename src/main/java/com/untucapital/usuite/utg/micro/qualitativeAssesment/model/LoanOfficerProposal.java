package com.untucapital.usuite.utg.micro.qualitativeAssesment.model;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LoanOfficerPro")
public class LoanOfficerProposal extends AbstractEntity {
    private String loanId;
    private double amount;
    private String maturity;
    private double instalments;
    private double interestRate;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMaturity() {
        return maturity;
    }

    public void setMaturity(String maturity) {
        this.maturity = maturity;
    }

    public double getInstalments() {
        return instalments;
    }

    public void setInstalments(double instalments) {
        this.instalments = instalments;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
