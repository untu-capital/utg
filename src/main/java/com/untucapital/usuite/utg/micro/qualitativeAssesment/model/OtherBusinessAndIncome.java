package com.untucapital.usuite.utg.micro.qualitativeAssesment.model;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class OtherBusinessAndIncome extends AbstractEntity {
    private String loanId;
    private String description;
    private String since;
    private Long monthlyNetIncome;

    public OtherBusinessAndIncome() {
    }

    public OtherBusinessAndIncome(String loanId, String description, String since, Long monthlyNetIncome) {
        this.loanId = loanId;
        this.description = description;
        this.since = since;
        this.monthlyNetIncome = monthlyNetIncome;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public Long getMonthlyNetIncome() {
        return monthlyNetIncome;
    }

    public void setMonthlyNetIncome(Long monthlyNetIncome) {
        this.monthlyNetIncome = monthlyNetIncome;
    }

    @Override
    public String toString() {
        return "OtherBusinessAndIncomeService{" +
                "loanId='" + loanId + '\'' +
                ", description='" + description + '\'' +
                ", since='" + since + '\'' +
                ", monthlyNetIncome=" + monthlyNetIncome +
                '}';
    }
}
