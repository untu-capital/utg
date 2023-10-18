package com.untucapital.usuite.utg.model.transactions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionInfo {
    private int toAccount;
    private String transactionType;
    private int exchangeRate;
    private String description;
    private int fromAccount;
    private String reference;
    private String currency;
    private Double amount;
    private LocalDate transactionDate;

}
