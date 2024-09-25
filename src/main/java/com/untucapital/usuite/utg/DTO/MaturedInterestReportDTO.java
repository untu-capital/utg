package com.untucapital.usuite.utg.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MaturedInterestReportDTO {
    private LocalDate transactionDate;         // Date of the transaction
    private String transactionDescription;     // Description of the transaction (e.g., interest applied, repayment)
    private double debit;                      // Debit amount for the transaction
    private double credit;                     // Credit amount for the transaction
    private double balance;                    // Running balance after the transaction

    // Getters and Setters
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

