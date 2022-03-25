package com.untucapital.usuite.utg.micro.qualitativeAssesment.model;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FixedAssetsMicro")
public class FixedAssets extends AbstractEntity {
    private String loanId;
    private String  description;
    private String purchaseYear;
    private String purchasePrice;
    private String marketValue;

    public FixedAssets() {
    }

    public FixedAssets(String loanId, String description, String purchaseYear, String purchasePrice, String marketValue) {
        this.loanId = loanId;
        this.description = description;
        this.purchaseYear = purchaseYear;
        this.purchasePrice = purchasePrice;
        this.marketValue = marketValue;
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

    public String getPurchaseYear() {
        return purchaseYear;
    }

    public void setPurchaseYear(String purchaseYear) {
        this.purchaseYear = purchaseYear;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    @Override
    public String toString() {
        return "FixedAssets{" +
                "loanId='" + loanId + '\'' +
                ", description='" + description + '\'' +
                ", purchaseYear='" + purchaseYear + '\'' +
                ", purchasePrice='" + purchasePrice + '\'' +
                ", marketValue='" + marketValue + '\'' +
                '}';
    }
}
