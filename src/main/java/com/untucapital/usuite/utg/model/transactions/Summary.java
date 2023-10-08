package com.untucapital.usuite.utg.model.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Summary {
    private Currency currency;
    private double principalDisbursed;
    private double principalPaid;
    private double principalWrittenOff;
}
