package com.untucapital.usuite.utg.micro.qualitativeAssesment.model;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AccountsReceivablesMicro")
public class AccountsReceivable extends AbstractEntity {
    private String loanId;
    private String customerName;
    private String dueSince;
    private String expectedPayment;
    private String paymentProbability;
    private String debtValue;

    public AccountsReceivable() {
    }

    public AccountsReceivable(String loanId, String customerName, String dueSince, String expectedPayment, String paymentProbability, String debtValue) {
        this.loanId = loanId;
        this.customerName = customerName;
        this.dueSince = dueSince;
        this.expectedPayment = expectedPayment;
        this.paymentProbability = paymentProbability;
        this.debtValue = debtValue;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDueSince() {
        return dueSince;
    }

    public void setDueSince(String dueSince) {
        this.dueSince = dueSince;
    }

    public String getExpectedPayment() {
        return expectedPayment;
    }

    public void setExpectedPayment(String expectedPayment) {
        this.expectedPayment = expectedPayment;
    }

    public String getPaymentProbability() {
        return paymentProbability;
    }

    public void setPaymentProbability(String paymentProbability) {
        this.paymentProbability = paymentProbability;
    }

    public String getDebtValue() {
        return debtValue;
    }

    public void setDebtValue(String debtValue) {
        this.debtValue = debtValue;
    }

    @Override
    public String toString() {
        return "AccountsReceivable{" +
                "customerName='" + customerName + '\'' +
                ", dueSince='" + dueSince + '\'' +
                ", expectedPayment='" + expectedPayment + '\'' +
                ", paymentProbability='" + paymentProbability + '\'' +
                ", debtValue='" + debtValue + '\'' +
                '}';
    }
}
