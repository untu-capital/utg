package com.untucapital.usuite.utg.micro.qualitativeAssesment.model;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RepaymentHistoryMicro")
public class RepaymentHistory extends AbstractEntity {
    private String loanId;
    private String lendingInstitution;
    private Long amount;
    private String dateOfDisb;
    private String maturity;
    private String outstandingInstallments;
    private Long totalDaysInArrears;
    private Long averageDaysInArrears;
    private String classification;

    public RepaymentHistory() {
    }

    public RepaymentHistory(String loanId, String lendingInstitution, Long amount, String dateOfDisb, String maturity, String outstandingInstallments, Long totalDaysInArrears, Long averageDaysInArrears, String classification) {
        this.loanId = loanId;
        this.lendingInstitution = lendingInstitution;
        this.amount = amount;
        this.dateOfDisb = dateOfDisb;
        this.maturity = maturity;
        this.outstandingInstallments = outstandingInstallments;
        this.totalDaysInArrears = totalDaysInArrears;
        this.averageDaysInArrears = averageDaysInArrears;
        this.classification = classification;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getDateOfDisb() {
        return dateOfDisb;
    }

    public void setDateOfDisb(String dateOfDisb) {
        this.dateOfDisb = dateOfDisb;
    }

    public String getMaturity() {
        return maturity;
    }

    public void setMaturity(String maturity) {
        this.maturity = maturity;
    }

    public String getLendingInstitution() {
        return lendingInstitution;
    }

    public void setLendingInstitution(String lendingInstitution) {
        this.lendingInstitution = lendingInstitution;
    }

    public String getOutstandingInstallments() {
        return outstandingInstallments;
    }

    public void setOutstandingInstallments(String outstandingInstallments) {
        this.outstandingInstallments = outstandingInstallments;
    }

    public Long getTotalDaysInArrears() {
        return totalDaysInArrears;
    }

    public void setTotalDaysInArrears(Long totalDaysInArrears) {
        this.totalDaysInArrears = totalDaysInArrears;
    }

    public Long getAverageDaysInArrears() {
        return averageDaysInArrears;
    }

    public void setAverageDaysInArrears(Long averageDaysInArrears) {
        this.averageDaysInArrears = averageDaysInArrears;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Override
    public String toString() {
        return "RepaymentHistory{" +
                "loanId='" + loanId + '\'' +
                ", amount=" + amount +
                ", dateOfDisb='" + dateOfDisb + '\'' +
                ", maturity='" + maturity + '\'' +
                ", outstandingInstallments='" + outstandingInstallments + '\'' +
                ", totalDaysInArrears=" + totalDaysInArrears +
                ", averageDaysInArrears=" + averageDaysInArrears +
                ", classification='" + classification + '\'' +
                '}';
    }
}
