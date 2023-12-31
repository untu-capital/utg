package com.untucapital.usuite.utg.micro.qualitativeAssesment.model;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Past3MonthsMicro extends AbstractEntity {
    @Column(nullable = false)
    private String loanId;
    private double month1;
    private double month2;
    private double month3;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public double getMonth1() {
        return month1;
    }

    public void setMonth1(double month1) {
        this.month1 = month1;
    }

    public double getMonth2() {
        return month2;
    }

    public void setMonth2(double month2) {
        this.month2 = month2;
    }

    public double getMonth3() {
        return month3;
    }

    public void setMonth3(double month3) {
        this.month3 = month3;
    }
}
