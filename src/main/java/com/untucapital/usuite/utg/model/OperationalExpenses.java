package com.untucapital.usuite.utg.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "OperationExpensesMicro")
public class OperationalExpenses extends AbstractEntity {
    private String loanId;
    private String name;
    private String comment;
    private double amount;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
