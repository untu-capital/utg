package com.untucapital.usuite.utg.micro.qualitativeAssesment.model;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "InventoryMicro")
public class Inventory extends AbstractEntity {
    private String loanId;
    private String product;
    private String quantity;
    private String purchasePrice;
    private String salePrice;

    public Inventory() {
    }

    public Inventory(String loanId, String product, String quantity, String purchasePrice, String salePrice) {
        this.loanId = loanId;
        this.product = product;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "loanId='" + loanId + '\'' +
                ", product='" + product + '\'' +
                ", quantity='" + quantity + '\'' +
                ", purchasePrice='" + purchasePrice + '\'' +
                ", salePrice='" + salePrice + '\'' +
                '}';
    }
}
