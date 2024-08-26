package com.untucapital.usuite.utg.model.transactions.interim.dto;

import com.untucapital.usuite.utg.model.transactions.Currency;
import com.untucapital.usuite.utg.model.transactions.PaymentDetailData;
import com.untucapital.usuite.utg.model.transactions.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private int id;
    private String officeName;
    private TransactionTypeDTO type;
    private LocalDate date;
    private CurrencyDTO currency;
    private PaymentDetailData paymentDetailData;
    private double amount;
    private String submittedByUsername;
    private TransferDTO transfer;

      /*
     {
    "date": "2022-12-02",
    "period": "6",
    "totalOutstanding": "8581.31",
    "totalPaid": "0",
    "paidBy": "",
    "totalDue": "8581.31",
    "penaltyFee": 429.0655,.....
    "newDate": "2024-08-31",....
    "loanId": "30816"
  }
     */
}

