package com.untucapital.usuite.utg.micro.qualitativeAssesment.model;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SalesPurchaseCogsGrossMarginMicro")
public class SalesPurchaseCogsGrossMargin extends AbstractEntity {
    private String loanId;
    private String bookExist;
    private String basisOfSales;
    private String salesCalculation;
    private String purchaseCalculation;

    public SalesPurchaseCogsGrossMargin() {
    }

    public SalesPurchaseCogsGrossMargin(String loanId, String bookExist, String basisOfSales, String salesCalculation, String purchaseCalculation) {
        this.loanId = loanId;
        this.bookExist = bookExist;
        this.basisOfSales = basisOfSales;
        this.salesCalculation = salesCalculation;
        this.purchaseCalculation = purchaseCalculation;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getBookExist() {
        return bookExist;
    }

    public void setBookExist(String bookExist) {
        this.bookExist = bookExist;
    }

    public String getBasisOfSales() {
        return basisOfSales;
    }

    public void setBasisOfSales(String basisOfSales) {
        this.basisOfSales = basisOfSales;
    }

    public String getSalesCalculation() {
        return salesCalculation;
    }

    public void setSalesCalculation(String salesCalculation) {
        this.salesCalculation = salesCalculation;
    }

    public String getPurchaseCalculation() {
        return purchaseCalculation;
    }

    public void setPurchaseCalculation(String purchaseCalculation) {
        this.purchaseCalculation = purchaseCalculation;
    }

    @Override
    public String toString() {
        return "SalesPurchaseCogsGrossMarginRepository{" +
                "loanId='" + loanId + '\'' +
                ", bookExist='" + bookExist + '\'' +
                ", basisOfSales='" + basisOfSales + '\'' +
                ", salesCalculation='" + salesCalculation + '\'' +
                ", purchaseCalculation='" + purchaseCalculation + '\'' +
                '}';
    }
}
