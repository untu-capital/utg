package com.untucapital.usuite.utg.model.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionType {

    private int id;
    private String code;
    private String value;
    private boolean disbursement;
    private boolean repaymentAtDisbursement;
    private boolean repayment;
    private boolean contra;
    private boolean waiveInterest;
    private boolean waiveCharges;
    private boolean accrual;
    private boolean writeOff;
    private boolean recoveryRepayment;
    private boolean initiateTransfer;
    private boolean approveTransfer;
    private boolean withdrawTransfer;
    private boolean rejectTransfer;
    private boolean chargePayment;
    private boolean refund;
    private boolean refundForActiveLoans;
    private boolean suspendedIncome;
    private boolean reverseSuspendedIncome;
}
