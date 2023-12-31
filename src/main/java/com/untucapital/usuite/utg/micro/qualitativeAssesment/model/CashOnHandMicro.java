package com.untucapital.usuite.utg.micro.qualitativeAssesment.model;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CashOnHandMicro extends AbstractEntity {

    @Column(nullable = false)
    private String loanId;
    private String time;
    private String dateOfLastPurchase;
    private double amountOfCostPurchase;
    private double cash;
    private double averageSales;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDateOfLastPurchase() {
        return dateOfLastPurchase;
    }

    public void setDateOfLastPurchase(String dateOfLastPurchase) {
        this.dateOfLastPurchase = dateOfLastPurchase;
    }

    public double getAmountOfCostPurchase() {
        return amountOfCostPurchase;
    }

    public void setAmountOfCostPurchase(double amountOfCostPurchase) {
        this.amountOfCostPurchase = amountOfCostPurchase;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getAverageSales() {
        return averageSales;
    }

    public void setAverageSales(double averageSales) {
        this.averageSales = averageSales;
    }
}
