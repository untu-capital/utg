package com.untucapital.usuite.utg.micro.qualitativeAssesment.model;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Past3WorkingDaysMicro extends AbstractEntity {
    @Column(nullable = false)
    private String loanId;
    private double day1;
    private double day2;
    private double day3;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public double getDay1() {
        return day1;
    }

    public void setDay1(double day1) {
        this.day1 = day1;
    }

    public double getDay2() {
        return day2;
    }

    public void setDay2(double day2) {
        this.day2 = day2;
    }

    public double getDay3() {
        return day3;
    }

    public void setDay3(double day3) {
        this.day3 = day3;
    }
}
