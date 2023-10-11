package com.untucapital.usuite.utg.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisbursedLoanMonth {

    private String month;
    private int numberOfDisbursedLoans;
    private List<DisbursedLoan> disbursedLoans;
    private BigDecimal totalPrincipalDisbursed;

}
